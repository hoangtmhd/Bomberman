package entities.character.enemy.ai;

import app.management.map.Direction;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();

    //tinh toan duong di
    public abstract Direction calculateDirection();

}
