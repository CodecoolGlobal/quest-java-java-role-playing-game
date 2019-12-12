package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.items.Item;

public abstract class Actor implements Drawable {
    Item stolenItem = null;
    protected Cell cell;
    protected int health;
    protected final int originalHealth = 20;
    protected int attack;
    protected int defense;
    protected boolean isDead = false;

    protected Actor(Cell cell, int health, int attack, int defense) {
        this.cell = cell;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.cell.setActor(this);
    }

    protected abstract void attack(Actor enemy);

    protected abstract void die(Cell cell);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isDead() {
        return isDead;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell){this.cell = cell;}

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public Item getStolenItem() {
        return stolenItem;
    }

    public void setStolenItem(Item stolenItem) {
        this.stolenItem = stolenItem;
    }

    public void setXY(int x, int y) {
        cell.setX(x);
        cell.setY(y);
    }
}
