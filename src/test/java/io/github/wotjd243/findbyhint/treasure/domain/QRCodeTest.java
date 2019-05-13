package io.github.wotjd243.findbyhint.treasure.domain;

import org.assertj.core.api.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QRCodeTest {
    @Test
    public void QR코드_생성(){
        //given
        String pwsd ="1234";
        //when
        QRCode qrCode = QRCode.valueOf(pwsd);
        //then

        assertThat(qrCode.getQrPw()).isEqualTo(pwsd);

    }

}