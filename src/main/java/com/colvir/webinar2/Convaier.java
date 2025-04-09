package com.colvir.webinar2;

public class Convaier {

    private static Convaier INSTANCE;

    private Convaier() {
    }

    public static Convaier getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Convaier();
        }
        return INSTANCE;
    }

    public void process() {
    }
}
