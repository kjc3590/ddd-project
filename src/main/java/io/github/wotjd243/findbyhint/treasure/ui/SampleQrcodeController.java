package io.github.wotjd243.findbyhint.treasure.ui;


import io.github.wotjd243.findbyhint.treasure.domain.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
/**
 *
 * @author DoYoung
 *
 */

@Controller
public class SampleQrcodeController {

    //controller
    @GetMapping(value="/makeQR")
    public View getQrCode(){

        //default config

        String url = "https://blog.naver.com/doyoung0205";
        QRCode sampleQrCodeVO= QRCode.valueOf("비밀번호");
        return sampleQrCodeVO.getQrView();
}


}
