package entities.changeable.character.Enemy;

import entities.EntityMapData;
import entities.changeable.character.Character;
import graphics.Sprite;

import java.util.ArrayList;

public abstract class Enemy extends Character {
    public Enemy(int xUnit, int yUnit, Sprite sprite, ArrayList<ArrayList<EntityMapData>> mapData) {
        super(xUnit, yUnit, sprite, mapData);
    }
}
