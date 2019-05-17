package io.github.wotjd243.findbyhint.treasure.application;

import io.github.wotjd243.findbyhint.treasure.domain.QRCode;
import io.github.wotjd243.findbyhint.treasure.domain.Treasure;
import io.github.wotjd243.findbyhint.treasure.domain.TreasureRepository;
import io.github.wotjd243.findbyhint.util.AES256Cipher;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log
public class QrCodeService {

    private final TreasureRepository treasureRepository;
    private final TreasureService treasureService;

    public QrCodeService(TreasureRepository treasureRepository, TreasureService treasureService) {
        this.treasureRepository = treasureRepository;
        this.treasureService = treasureService;
    }

    // TODO (5) 하나의 트랜잭션에 메소드 만들기
    @Transactional
    public String takeTreasure(String url){
        Long treasureId = treasureService.getTreasureIdByActive();
        if(!treasureService.isExist(treasureId)){
            new IllegalAccessException("진행중인 보물이 없습니다.");
        }

        Treasure treasure = treasureService.getTreasure(treasureId);
        if(!urlMatch(url,treasure.getQrCodeVO())){
            new IllegalAccessException("잘못된 url 입니다.");
        }

        // COMPLETED 비밀번호 화면에 보여주어야함
        AES256Cipher cipher =AES256Cipher.getInstance();
        String pwsd = cipher.AES_Decode(treasure.getQrCodeVO().getQrPw());

        // COMPLETED 동시에 보물에 상태를 완료로 변경
        treasure.closeEvent();
        treasureRepository.save(treasure);

        // TODO (4) 이벤트 발생으로 합격자 테이블에 들어감



        return pwsd;

    }





    // COMPLETED 해당 QR 코드 를 찍고 들어왔을때 url 의 있는 값이 맞는지 확인
    private boolean urlMatch(String url, QRCode qrCode){
        AES256Cipher cipher =AES256Cipher.getInstance();
        url = cipher.AES_Decode(url);
        String pwsd = cipher.AES_Decode(qrCode.getQrPw());

        log.info("url :: '"+url+"'");
        log.info("pwsd :: '"+ pwsd+"'");

        return url.equals(pwsd);
    }











}
