package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.items.Item;

public class Stealer extends Actor implements Aggro{

    public Stealer(Cell cell){super(cell, 20, 2, 0); }

    @Override
    void attack(Actor enemy) {
        if (enemy.health > 0) {
            if(this.attack - enemy.defense > 0) {
                enemy.health -= this.attack - enemy.defense;
            }
        } else {
            enemy.die(enemy.cell);
        }
    }

    @Override
    void die(Cell cell) {
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
        /*for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Cell neighbour = cell.getNeighbor(i, j);
                if (!Objects.isNull(neighbour.getActor()) &&
                        neighbour.getActor().getTileName().contains("player")) {
                    aggroStatus = true;
                }
            }
        }
    }*/
    }

    @Override
    public void move(int dx, int dy) {

    }


    public boolean isAggroStatus() {
        return false;
    }



}
