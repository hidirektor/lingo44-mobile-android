package com.hidirektor.lingo44.Utility.Models.LevelExam.Basic;

import java.util.List;

public class ExamModel {
    private List<QuestionModel> questions;
    private int currentQuestionIndex;
    private long startTime;
    private final int timeLimit = 30 * 60; // 30 minutes in seconds

    public ExamModel(List<QuestionModel> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
    }

    public void startExam() {
        startTime = System.currentTimeMillis();
    }

    public void submitAnswer(int questionIndex, String selectedAnswer, String confidence) {
        QuestionModel question = questions.get(questionIndex);
        question.setSelectedAnswer(selectedAnswer);
        question.setConfidence(confidence);
    }

    public long getRemainingTime() {
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // elapsed time in seconds
        return Math.max(timeLimit - elapsedTime, 0); // remaining time in seconds
    }

    public QuestionModel getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        }
    }

    public void previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
        }
    }

    // Method to calculate the number of correct answers
    public int getCorrectAnswersCount() {
        int correctAnswers = 0;
        for (QuestionModel question : questions) {
            if (question.isAnswerCorrect()) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }
}