package io.github.wotjd243.findbyhint.treasure.domain;
/**
 *
 * @author DoYoung
 *
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class QRCodeVO {

    @Column(nullable = false)
    private String qrUrl;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private String qrColor;

    @Column(nullable = false)
    private String backColor;

    @Column(nullable = false)
    private String qrPw;

    public QRCodeVO(String qrUrl, int width, int height, String qrColor, String backColor,String qrPw) {
        this.qrUrl = qrUrl;
        this.width = width;
        this.height = height;
        this.qrColor = qrColor;
        this.backColor = backColor;
        this.qrPw = qrPw;
    }


    public BitMatrix makeQRMatrix(){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix matrix = null;
        MatrixToImageConfig config = null;
        //BufferedImage  qrImage = null;
        try {
            this.qrUrl = new String(this.qrUrl.getBytes("UTF-8"), "ISO-8859-1");

            matrix = qrCodeWriter.encode(this.qrUrl, BarcodeFormat.QR_CODE, width, height);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    public MatrixToImageConfig makeColorConfig(){

        int intQrColor = 0;
        int intBackColor = 0;

        try{
            intQrColor = Integer.parseUnsignedInt(qrColor,16);
        }catch(Exception e){
            intQrColor = 0xff000000;
        }

        try{
            intBackColor = Integer.parseUnsignedInt(backColor,16);
        }catch(Exception e){
            intBackColor = 0xffffffff;
        }


        return new MatrixToImageConfig(intQrColor, intBackColor);
    }

    //view
     public View getQrView() {
        return new AbstractView() {

            final BitMatrix matrix = makeQRMatrix();

            final MatrixToImageConfig config = makeColorConfig();


            @Override
            protected void renderMergedOutputModel(Map<String, Object> model,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {

                response.setContentType("image/png; charset=UTF-8");
                response.setHeader("Content-Transfer-Encoding", "binary");

                OutputStream out = response.getOutputStream();

                MatrixToImageWriter.writeToStream(matrix, "png", out, config);
                out.flush();
            }

        };
    }

    public boolean isEmpty() {
        return  StringUtils.isEmpty(this.qrUrl) || StringUtils.isEmpty(this.width) || StringUtils.isEmpty(this.height)
                || StringUtils.isEmpty(this.qrColor) || StringUtils.isEmpty(this.backColor) || StringUtils.isEmpty(this.qrPw);
    }


    @Override
    public String toString() {
        return "QRCodeVO{" +
                "qrUrl='" + qrUrl + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", qrColor='" + qrColor + '\'' +
                ", backColor='" + backColor + '\'' +
                ", qrPw='" + qrPw + '\'' +
                '}';
    }

}
