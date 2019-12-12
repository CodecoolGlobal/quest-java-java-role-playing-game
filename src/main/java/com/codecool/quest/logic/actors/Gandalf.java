package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Gandalf extends Actor implements Aggro {

    public Gandalf(Cell cell) {
        super(cell, 50, 10, 10);
    }

    @Override
    void die(Cell cell) {

    }

    @Override
    public String getTileName() {
        return "gandalf";
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
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }
}

