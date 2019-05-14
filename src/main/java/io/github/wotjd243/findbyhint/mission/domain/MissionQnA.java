package io.github.wotjd243.findbyhint.mission.domain;

import javax.persistence.Embeddable;

@Embeddable
public class MissionQnA {
    private final String question;
    private final String answer;

    public MissionQnA(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
