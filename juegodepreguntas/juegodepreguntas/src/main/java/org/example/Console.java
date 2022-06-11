package org.example;

import org.example.actions.PlayerCreation;
import org.example.actions.QuestionCreation;
import org.example.model.Player;
import org.example.model.Question;

import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Database.init();
        start();
    }

    private static void start() {
        String opcion = chooseOption();

        if (opcion.equals("1")) {
            QuestionCreation qc = new QuestionCreation();
            Question question = qc.create();
            Database.save(question, "questions");
            start();
        } else if (opcion.equals("2")) {
            PlayerCreation playerCreator = new PlayerCreation();
            Player player = playerCreator.create();
            Game game = new Game(player);
            Player resultadoPlayer = game.start();
            System.out.println("Nombre: " +resultadoPlayer.getName() + " Obtuvo " + resultadoPlayer.getScore() + " puntos ");
            Database.save(resultadoPlayer, "players");
        } else if (opcion.equals("3")) {
            System.out.println("El usuario eligió salir del juego");
            System.exit(0);
        } else {
            chooseOption();
        }
    }

    private static String chooseOption() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elija una opción");
        System.out.println("1. Crear preguntas");
        System.out.println("2. Iniciar un juego nuevo");
        System.out.println("3. Cerrar aplicación");
        return sc.nextLine();
    }
}
