package FominaKat;

import FominaKat.MontyHall.GameMontyHall;

import java.util.Random;

public class Main {

    private static Random random = new Random();
    private static int countWin;


    public static void main(String[] args) {
        int countGame = 1000;
        GameMontyHall game = new GameMontyHall();

        for (int i = 0; i < countGame; i++) {

            game.newGame(random.nextInt(3));
            game.choicePlayer(random.nextInt(3));
            if (game.playGame(true)) {
                countWin++;
            }
        }

        System.out.println("Из " + countGame + " игр игрок выиграл " + countWin);
    }
}