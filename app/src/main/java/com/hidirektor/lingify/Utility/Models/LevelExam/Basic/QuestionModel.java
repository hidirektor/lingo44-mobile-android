package com.hidirektor.lingify.Utility.Models.LevelExam.Basic;

public class QuestionModel {
    private String questionText;
    private String[] answers;
    private String correctAnswer; // Correct answer
    private String[] confidenceLevels;
    private String selectedAnswer;
    private String confidence;

    public QuestionModel(String questionText, String[] answers, String correctAnswer, String[] confidenceLevels) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.confidenceLevels = confidenceLevels;
        this.selectedAnswer = null;
        this.confidence = null;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getConfidenceLevels() {
        return confidenceLevels;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public boolean isAnswerCorrect() {
        return selectedAnswer != null && selectedAnswer.equals(correctAnswer);
    }
}