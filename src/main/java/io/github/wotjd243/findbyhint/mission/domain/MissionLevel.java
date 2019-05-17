package io.github.wotjd243.findbyhint.mission.domain;

public enum MissionLevel {
    BRONZE(1,2, "easy"),
    SILVER(2,4, "medium"),
    GOLD(3,8, "hard");

    private final int levelValue;
    private final int hintCounter;
    private final String levelName;

    MissionLevel(int levelValue, int hintCounter, String levelName) {
        this.levelValue = levelValue;
        this.hintCounter = hintCounter;
        this.levelName = levelName;
    }

    public int getLevelValue() {
        return levelValue;
    }

    public String getLevelName() {
        return levelName;
    }

    public int getHintCounter() { return hintCounter;}

    public static int size(){ return values().length; }


}