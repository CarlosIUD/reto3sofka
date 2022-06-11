package org.example.actions;

import org.example.model.Player;

import java.util.Scanner;

public class PlayerCreation {


    public Player create() {
        String name = chooseName();
        return new Player(name, 0);
    }

    private String chooseName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba su nombre");
        return sc.nextLine();
    }
}
