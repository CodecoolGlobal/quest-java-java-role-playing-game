package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.environment.Fireball;

public class Gandalf extends Actor implements Aggro {

    public Gandalf(Cell cell) {
        super(cell, 50, 10, 10);
    }

    public void fireWall(GameMap map){

        for(int i=0; i<map.getWidth(); i++){
            Cell cell = map.getCell(i, 0);
            Fireball ball = new Fireball(cell);
            cell.setActor(ball);
            for(int j=0; j<map.getHeight();j++) {
                ball.move(0, 1);
            }
        }
    }

    @Override
    protected void die(Cell cell) {

    }

    @Override
    public String getTileName() {
        return "gandalf";
    }

    @Override
    protected void attack(Actor enemy) {

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

