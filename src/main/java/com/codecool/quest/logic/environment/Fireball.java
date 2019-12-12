package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Aggro;
import com.codecool.quest.logic.actors.Player;

import java.util.Objects;

public class Fireball extends Actor implements Drawable, Aggro {


    public Fireball(Cell cell) {
        super(cell, 50, 10, 10);
    }

    @Override
    public String getTileName() {
        return "fireball";
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
        if (!Objects.isNull(nextCell.getActor()) && nextCell.getActor().getTileName().contains("player")) {
            attack(nextCell.getActor());
            die(cell);
        } else if (!nextCell.getType().isSteppable()) {
            die(cell);
        } else {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    protected void attack(Actor enemy) {
        enemy.setHealth(enemy.getHealth()-attack);
        if (enemy.getHealth() < 0) {
            Player player = (Player) enemy;
            player.die(enemy.getCell());
        }
    }

    @Override
    protected void die(Cell cell) {
        this.isDead = true;
        cell.setActor(null);
    }
}
