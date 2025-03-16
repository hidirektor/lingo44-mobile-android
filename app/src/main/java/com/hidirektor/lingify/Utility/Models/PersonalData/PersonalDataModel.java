package com.hidirektor.lingify.Utility.Models.PersonalData;

import java.util.LinkedList;

public class PersonalDataModel {
    private String question;
    private LinkedList<AnswerModel> answerList;

    public PersonalDataModel(String question, LinkedList<AnswerModel> answerList) {
        this.question = question;
        this.answerList = answerList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LinkedList<AnswerModel> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(LinkedList<AnswerModel> answerList) {
        this.answerList = answerList;
    }
}
