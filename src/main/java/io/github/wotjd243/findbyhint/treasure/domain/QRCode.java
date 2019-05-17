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
import io.github.wotjd243.findbyhint.util.AES256Cipher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;



@Embeddable
public class QRCode {

    //기본생성자
    public QRCode(){}

    private final static String prefixUrl = "http://localhost:8080/findByHint?url=";

    @Column(nullable = false)
    private String qrUrl;

    @Column(nullable = false)
    private String qrPw;

// qrColor ="0xff000000";
// backColor ="0xffffffff";

    private QRCode(String qrPw) {

        validation(qrPw);

        AES256Cipher cipher = AES256Cipher.getInstance();
        String aes_encode_pw = cipher.AES_Encode(qrPw);

        this.qrUrl = prefixUrl+aes_encode_pw;
        this.qrPw = aes_encode_pw;
    }

    public static QRCode valueOf(String qrPw){
        return new QRCode(qrPw);
    }

    public String getQrPw(){
        AES256Cipher cipher = AES256Cipher.getInstance();
        return cipher.AES_Decode(this.qrPw);

    }


    public String getQrUrl(){
        return this.qrUrl;
    }

    private void validation(String qrPw) {


        if(StringUtils.isEmpty(qrPw)){
            throw new IllegalArgumentException("QrCode 의 비밀번호를 할당받지 못했습니다.");
        }

    }


    public BitMatrix makeQRMatrix(){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix matrix = null;
        MatrixToImageConfig config = null;
        //BufferedImage  qrImage = null;
        try {
            this.qrUrl = new String(this.qrUrl.getBytes("UTF-8"), "ISO-8859-1");

            matrix = qrCodeWriter.encode(this.qrUrl, BarcodeFormat.QR_CODE, 300 , 300);


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
            intQrColor = Integer.parseUnsignedInt("qrColor",16);
        }catch(Exception e){
            intQrColor = 0xff000000;
        }

        try{
            intBackColor = Integer.parseUnsignedInt("0xffffffff",16);
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

    @Override
    public String toString() {
        return "QRCode{" +
                "qrUrl='" + qrUrl + '\'' +
                ", qrPw='" + qrPw + '\'' +
                '}';
    }
}
