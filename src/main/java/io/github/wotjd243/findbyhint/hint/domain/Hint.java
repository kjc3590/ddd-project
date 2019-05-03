package io.github.wotjd243.findbyhint.hint.domain;

public class Hint {

    // 깃테스트
    private final Long hintNum;
    private double radius;

    private Hint(final Long hintNum) {
        validate(hintNum);
        this.hintNum = hintNum;
    }

    // 헌트테이블의 PK값을 전해주는 정적 팩토리 메서드 만들기
    public static Hint valueOfHintNum (final Long hintNum){
        return new Hint(hintNum);
    }

    public void validate(final Long hintNum){
        if(hintNum < 1 || hintNum > 151){
            // 자바 표준 익셉션
            throw new IllegalArgumentException();
        }
    }

}

    // 로직설계하기
    // Trasure 클래스 TargetPoint 메서드 로 좌표값을 구해서 거기서 부터 원의 면적값과 반지름값을 구현한다.