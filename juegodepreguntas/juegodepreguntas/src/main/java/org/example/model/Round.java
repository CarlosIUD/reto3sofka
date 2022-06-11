package org.example.model;

import org.example.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Round {
    Question question;
    int score = 0;
    int roundID;

    public int getScore() {
        return score;
    }

    public Round(int roundID) {
        this.roundID = roundID;
    }

    public boolean start() {
        List<Question> roundQuestions = new ArrayList<>();
        List<Question> questions = Database.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (roundID == question.getCategory()) {
                roundQuestions.add(question);
            }
        }

        int randomNum = (int) (Math.random() * 4);
        Question question = roundQuestions.get(randomNum);
        Scanner scanner = new Scanner(System.in);
        System.out.println(question.getEnunciado());
        System.out.println(question.getAnswers().get(0).getEnunciado());
        System.out.println(question.getAnswers().get(1).getEnunciado());
        System.out.println(question.getAnswers().get(2).getEnunciado());
        System.out.println(question.getAnswers().get(3).getEnunciado());
        int opcionElegida = scanner.nextInt();

        if (question.getAnswers().get(opcionElegida - 1).isRight()) {
            score = score + question.getScore();
            System.out.println("Correcto.");
            return true;
        } else {
            return false;
        }
    }

}

