package org.example.actions;

import org.example.model.Answer;
import org.example.model.Question;

import java.util.List;
import java.util.Scanner;

public class QuestionCreation {

    public Question create() {
        System.out.println("Creando pregunta con start");
        String enunciado = chooseEnunciado();
        int score = chooseScore();
        int category = chooseCategory();

        AnswersCreation ac = new AnswersCreation();
        List<Answer> answers = ac.create();

        Question question = new Question(enunciado, score, category, answers);
        return question;
    }


    private String chooseEnunciado() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba el enunciado de la pregunta");
        return sc.nextLine();
    }

    private int chooseScore() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Asigne el puntaje de la preguntas");
        return sc.nextInt();
    }

    private int chooseCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Asigne la categoría de la pregunta");
        return sc.nextInt();
    }


}
