package io.github.wotjd243.findbyhint.mission.domain;

public enum MissionLevel {
    BRONZE(1,2),
    SILVER(2,4),
    GOLD(3,8);

    private final int levelValue;
    private final int hintCounter;

    MissionLevel(int levelValue, int hintCounter) {
        this.levelValue = levelValue;
        this.hintCounter = hintCounter;
    }

    public int getLevelValue() {
        return levelValue;
    }



    public int getHintCounter() { return hintCounter;}

    public static int size(){ return values().length; }


}