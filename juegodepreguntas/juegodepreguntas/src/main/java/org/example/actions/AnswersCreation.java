package org.example.actions;

import org.example.model.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnswersCreation {
    List<Answer> answers = new ArrayList<>();


    public List<Answer> create() {
        Answer answer1 = createAnswer();
        answers.add(answer1);

        Answer answer2 = createAnswer();
        answers.add(answer2);

        Answer answer3 = createAnswer();
        answers.add(answer3);

        Answer answer4 = createAnswer();
        answers.add(answer4);
        return answers;
    }

    private Answer createAnswer() {
        String enunciado = chooseEnunciado();
        boolean right = chooseIsRight();
        Answer answer = new Answer(enunciado, right);
        return answer;
    }

    private String chooseEnunciado () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba el enunciado de la respuesta");
        return sc.nextLine();
    }

    private boolean chooseIsRight() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba si la respuesta es correcta o no [true/false]");
        return sc.nextBoolean();
    }
}