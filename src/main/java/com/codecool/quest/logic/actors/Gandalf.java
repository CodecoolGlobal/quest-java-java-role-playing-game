package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Gandalf extends Actor implements Moveable {

    public Gandalf(Cell cell) {
        super(cell, 50, 10, 10);
    }

    @Override
    public void move(int dx, int dy) {

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
}

