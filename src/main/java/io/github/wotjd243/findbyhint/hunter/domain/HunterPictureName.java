package io.github.wotjd243.findbyhint.hunter.domain;

import io.github.wotjd243.findbyhint.util.check.Check;

import javax.persistence.Embeddable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Embeddable
public class HunterPictureName {

    private String hunterPictureName;

    public HunterPictureName() {
    }

    public HunterPictureName(String hunterPictureName) {
        validation(hunterPictureName);
        this.hunterPictureName = fileNameUnique(hunterPictureName);
    }

    private void validation(String hunterPictureName) {
        Check.imgCheck(hunterPictureName);
    }

    private String fileNameUnique(String hunterPictureName) {

        //파일 저장명 처리
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMDDHHmmss");
        String originalNameExtension = hunterPictureName.substring(hunterPictureName.lastIndexOf(".") + 1).toLowerCase();
        String today = dateFormat.format(new Date());
        String modifyName = today + "_" + UUID.randomUUID().toString().substring(20) + "." + originalNameExtension;

        return modifyName;

    }

    @Override
    public String toString() {
        return "HunterPictureName{" +
                "hunterPictureName='" + hunterPictureName + '\'' +
                '}';
    }
}
