package io.github.wotjd243.findbyhint.mission.domain;

public class MissionPoint {

    private static final int MINIMUM_POINT = 0;
    private static final int MAXIMUM_POINT = 100;

    MissionPoint(){}

    private int point;
    private MissionPoint(final int point) {
        validate(point);
        this.point = point;
    }

    //static 팩토리 메소드(정적 팩토리메소드)
    public static MissionPoint valueOf(final int point) {
        return new MissionPoint(point);
    }

    //유효성 검사
    private void validate(final int point) {
        if(point < MINIMUM_POINT || point > MAXIMUM_POINT) {
            throw new IllegalArgumentException("포인트는 0점 이상, 100점 이하여야 합니다.");
        }
    }
}
