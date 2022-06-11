package org.example.model;

public class Answer {
  private String enunciado;
    private boolean isRight;

    public Answer(String enunciado, boolean isRight) {
        this.enunciado = enunciado;
        this.isRight = isRight;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
