package jogo.Util;

import java.util.Random;

public class Dado {
    private Random rand = new Random();

    public int rolar(int faces) {
        return rand.nextInt(faces) + 1;
    }
}
