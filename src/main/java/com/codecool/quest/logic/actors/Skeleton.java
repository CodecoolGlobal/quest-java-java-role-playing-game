package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

import java.util.Objects;

public class Skeleton extends Actor implements Moveable {
    public Skeleton(Cell cell) {
        super(cell, 10, 2, 0);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (Objects.isNull(nextCell.getActor()) && !nextCell.getTileName().equals("wall")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

    }
}
