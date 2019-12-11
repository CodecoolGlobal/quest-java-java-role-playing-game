package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.items.Item;

import java.util.Objects;

public class Skeleton extends Actor implements Moveable, Aggro {
    private boolean aggroStatus = false;

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
        if (Objects.isNull(nextCell.getActor()) && nextCell.getType().isStepable()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public void die(Cell cell) {
        cell.setActor(null);
        this.isDead = true;
        cell.setType(CellType.REMAINS);
    }

    @Override
    public int calculateCoordinate(int playerCoordinate, int monsterCoordinate) {
        return Integer.compare(playerCoordinate, monsterCoordinate);
    }

    @Override
    public void aggro() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Cell neighbour = cell.getNeighbor(i, j);
                if (!Objects.isNull(neighbour.getActor()) &&
                        neighbour.getActor().getTileName().contains("player")) {
                    aggroStatus = true;
                }
            }
        }
    }

    public boolean isAggroStatus() {
        return aggroStatus;
    }
}
