package com.hidirektor.lingify.Utility.Models.PersonalSetup;

import java.util.LinkedList;

public class PersonalSetupModel {
    private String question;
    private LinkedList<PersonalSetupAnswerModel> answerList;
    private int selectedAnswerIndex = -1;

    public PersonalSetupModel(String question, LinkedList<PersonalSetupAnswerModel> answerList) {
        this.question = question;
        this.answerList = answerList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LinkedList<PersonalSetupAnswerModel> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(LinkedList<PersonalSetupAnswerModel> answerList) {
        this.answerList = answerList;
    }

    public void setSelectedAnswerIndex(int index) {
        this.selectedAnswerIndex = index;
    }
}
