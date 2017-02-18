package ru.itis.kpfu.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ruslan on 17.02.2017.
 */
@Component
public class ImplWarrior implements Warrior {
    Weapon weapon;
    Shield shield;

    @Autowired
    ImplWarrior(Weapon weapon,Shield shield) {
        this.weapon = weapon;
        this.shield = shield;
    }

    public String getShield() {
        return shield.getShield();
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public String getWeapon() {
        return weapon.getWeapon();
    }

    public void setWeapon(Weapon weapon) {this.weapon = weapon;}


}