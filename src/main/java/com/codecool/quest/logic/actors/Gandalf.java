package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.environment.Fireball;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.environment.Fireball;

public class Gandalf extends Actor implements Aggro {

    public Gandalf(Cell cell) {
        super(cell, 50, 10, 10);
    }

    public void fireWall(GameMap map) {

        Fireball[] fireWall = new Fireball[map.getWidth()];
        for (int i = 0; i < map.getWidth(); i++) {
            Cell cell = map.getCell(i, 0);
            Fireball ball = new Fireball(cell);
            cell.setActor(ball);
            fireWall[i] = ball;
        }
        for (int j = 0; j < map.getHeight(); j++) {
            for (Fireball f : fireWall) {
                f.move(0, 1);
            }
        }
    }

    @Override
    protected void die(Cell cell) {

    }

    @Override
    public String getTileName() {
        if (this.getHealth() > 10) {
            return "gandalf";
        } else {
            this.setAttack(15);
            return "dyingGandalf";
        }
    }


    @Override
    protected void attack(Actor enemy) {

    }

    @Override
    public int calculateCoordinate(int playerCoordinate, int monsterCoordinate) {
        return Integer.compare(playerCoordinate, monsterCoordinate);
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

