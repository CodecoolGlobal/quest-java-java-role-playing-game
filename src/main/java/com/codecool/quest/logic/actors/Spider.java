package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Aggro;
import com.codecool.quest.logic.actors.Player;

import java.util.Objects;

public class Spider extends Actor implements Aggro {

    public Spider(Cell cell) {
        super(cell, 0, 0, 0);
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
        if (Objects.isNull(nextCell.getActor())) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (!Objects.isNull(nextCell.getActor()) &&
                !nextCell.getActor().getTileName().equals("gandalf")) {
            attack(nextCell.getActor());
        }
    }

    @Override
    protected void attack(Actor enemy) {
        enemy.setHealth(enemy.getHealth() - (10 - enemy.getDefense()));
        this.isDead = true;
        if (enemy.getHealth() < 0) {
            Player player = (Player) enemy;
            player.die(player.getCell());
        }
    }

    @Override
    protected void die(Cell cell) {

    }

    @Override
    public String getTileName() {
        return "spider";
    }
}
