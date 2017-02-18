package ru.itis.kpfu.interfaces;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by ruslan on 17.02.2017.
 */

@Component
public class ImplWeapon implements Weapon {

    String weapon;

    ImplWeapon() {
        Random rnd = new Random();
        if (rnd.nextInt(3) == 2) {
            this.weapon = "Knife";
        } else {
            this.weapon = "Sword";
        }
    }

    public boolean isSword() {
        if (weapon.equals("Sword")) {
            return true;
        }
        return false;
    }

    public boolean isKnife() {
        if (weapon.equals("Knife")) {
            return true;
        }
        return false;
    }

    public String getWeapon() {
        return weapon;
    }
}
