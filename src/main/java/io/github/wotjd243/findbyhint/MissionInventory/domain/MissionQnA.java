package io.github.wotjd243.findbyhint.MissionInventory.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MissionQnA {
    private final String question;
    private final String answer;

    public MissionQnA(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public static MissionQnA valueOf(String question, String answer) {
        return new MissionQnA(question, answer);
    }

}
