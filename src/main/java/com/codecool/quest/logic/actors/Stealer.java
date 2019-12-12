package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.items.Item;

import java.util.Objects;

public class Stealer extends Actor implements Aggro{
    private boolean aggroStatus = false;

    public Stealer(Cell cell){super(cell, 20, 2, 0); }

    @Override
    protected void attack(Actor enemy) {
        if (enemy.health > 0) {
            if(this.attack - enemy.defense > 0) {
                enemy.health -= this.attack - enemy.defense;
            }
        } else {
            enemy.die(enemy.cell);
        }
    }

    @Override
    protected void die(Cell cell) {
        cell.setActor(null);
        this.isDead = true;
        cell.setType(CellType.REMAINS);
        cell.setItem(this.stolenItem);
    }

    @Override
    public String getTileName() {
        return "stealer";
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
                    System.out.println("AGGRO");
                }
            }
        }
    }

    @Override
    public void move(int dx, int dy) {
        aggro();
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (aggroStatus &&
                !isDead &&
                nextCell.getActor() != null &&
                nextCell.getActor().getTileName().contains("player")) {
            attack(nextCell.getActor());
        } else if (aggroStatus &&
                !isDead && nextCell.getType().isSteppable()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }


    public boolean isAggroStatus() {
        return false;
    }



}
