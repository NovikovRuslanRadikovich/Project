package ru.itis.kpfu.interfaces;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by ruslan on 17.02.2017.
 */
@Component
public class ImplShield implements Shield {

    String shield;

    ImplShield() {
        Random rnd = new Random();
        if (rnd.nextInt(3) == 3) {
            this.shield = "Ragged";
        } else {
            this.shield = "NotRagged";
        }
    }

    public boolean isRagged() {
        if(shield =="Ragged") {
            return true;
        }
        return false;
    }

    public String getShield() {
        return shield;
    }
}
