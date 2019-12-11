package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.items.Key;

public class Ogre extends Actor implements Aggro {
    public Ogre(Cell cell) {
        super(cell, 20, 7, 0);
    }

    @Override
    public String getTileName() {
        return "ogre";
    }

    @Override
    public void die(Cell cell) {
        cell.setActor(null);
        this.isDead = true;
        cell.setType(CellType.REMAINS);
        new Key(cell);
    }

    @Override
    void attack(Actor enemy) {
    }

    @Override
    public int calculateCoordinate(int playerCoordinate, int monsterCoordinate) {
        return 0;
    }

    @Override
    public void aggro() {

    }

    @Override
    public void move(int dx, int dy) {

    }
}