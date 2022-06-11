package org.example.model;

import java.util.List;

public class Question {
   private String enunciado;
    private int score;
    private int category;

    private List<Answer> answers;

    public Question(String enunciado, int score, int category, List<Answer> answers) {
        this.enunciado = enunciado;
        this.score = score;
        this.category = category;
        this.answers = answers;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

