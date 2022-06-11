package org.example;

import org.example.model.Player;
import org.example.model.Round;

import java.util.Scanner;

public class Game {
    Player player;
    int score = 0;
    int roundId;
    Round round;

    public Game(Player player) {
        this.player = player;
        roundId = 1;
    }

    public Player start() {
        boolean respuestaCorrecta = false;
        do {
            round = new Round(roundId);
            respuestaCorrecta = round.start();
            if (respuestaCorrecta) {
                score = score + round.getScore();

                Scanner scanner = new Scanner(System.in);
                System.out.println("¿Desea continuar en el juego? y/n");
                String eleccionUsuario = scanner.nextLine();
                if (eleccionUsuario.equals("y")) {

                } else if (eleccionUsuario.equals("n")) {
                    player.setScore(score);
                    return player;
                }
            }
            roundId = roundId + 1;
        } while (respuestaCorrecta && roundId <= 5);

        if (respuestaCorrecta) {
            System.out.println("GANASTE ");
        } else {
            System.out.println("Perdiste");
        }
        player.setScore(score);
        return player;
    }
}
