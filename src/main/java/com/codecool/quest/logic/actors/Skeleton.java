package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell, 10, 2, 0);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
