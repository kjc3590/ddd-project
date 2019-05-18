package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.treasure.domain.*;
import io.github.wotjd243.findbyhint.util.AES256Cipher;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

@Service
@Log
public class QrCodeService {

    private final TreasureRepository treasureRepository;
    private final TreasureService treasureService;
    private final TreasureEventService treasureEventService;

    public QrCodeService(TreasureRepository treasureRepository, TreasureService treasureService, TreasureEventService treasureEventService) {
        this.treasureRepository = treasureRepository;
        this.treasureService = treasureService;
        this.treasureEventService = treasureEventService;
    }

    @Transactional
    public String takeTreasure(String url){
        Long treasureId = treasureService.getTreasureIdByActive();

        Treasure treasure = treasureService.getTreasure(treasureId);
        if(!(urlMatch(url,treasure.getQrCodeVO()))){
            throw new IllegalArgumentException("잘못된 url 입니다.");
        }



        // COMPLETED 비밀번호 화면에 보여주어야함
        AES256Cipher cipher =AES256Cipher.getInstance();
        String pwsd = cipher.AES_Decode(treasure.getQrCodeVO().getQrPw());

        // COMPLETED 동시에 보물에 상태를 완료로 변경
        treasure.closeEvent();
        treasureRepository.save(treasure);

        // COMPLETED (4) 이벤트 발생 모든 헌터들에게 보물완료 되었다고 알려주기
        treasureEventService.sendFindMesseage();

        return pwsd;

    }

    public View currentQR(){
        Long treasureId = treasureService.getTreasureIdByActive();

        if(!treasureService.isExist(treasureId)){
           throw new IllegalArgumentException("현재 진행중인 이벤트가 없어 QR코드를 못불러옵니다.");
        }

        return treasureService.getTreasure(treasureId).qrCodeView();
    }






    // COMPLETED 해당 QR 코드 를 찍고 들어왔을때 url 의 있는 값이 맞는지 확인
    private boolean urlMatch(String url, QRCode qrCode){
        AES256Cipher cipher =AES256Cipher.getInstance();
        url = cipher.AES_Decode(url);
        String pwsd = cipher.AES_Decode(qrCode.getQrPw());

        log.info("url :: '"+url+"'");
        log.info("pwsd :: '"+ pwsd+"'");

        log.info(" url.equals(pwsd) :: "+  url.equals(pwsd));

        return url.equals(pwsd);
    }







}
