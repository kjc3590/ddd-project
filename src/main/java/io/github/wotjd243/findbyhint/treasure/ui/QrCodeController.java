package io.github.wotjd243.findbyhint.treasure.ui;


import io.github.wotjd243.findbyhint.treasure.application.QrCodeService;
import io.github.wotjd243.findbyhint.treasure.application.TreasureService;
import io.github.wotjd243.findbyhint.treasure.domain.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
/**
 *
 * @author DoYoung
 *
 */

@Controller
public class QrCodeController {


    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(value="/currentQR")
    public View getQrCode(){
        return qrCodeService.currentQR();
    }


    @GetMapping("/treasure/findByHint")
    public String findByHint(Model model, String url){

        model.addAttribute("pwsd",qrCodeService.takeTreasure(url));

        return  "pwsd";
    }



}
