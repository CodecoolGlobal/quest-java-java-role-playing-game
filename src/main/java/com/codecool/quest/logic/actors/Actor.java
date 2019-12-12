package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.items.Item;

public abstract class Actor implements Drawable {
    public Cell cell;
    int health;
    final int originalHealth = 20;
    int attack;
    int defense;
    boolean isDead = false;
    Item stolenItem = null;


    Actor(Cell cell, int health, int attack, int defense) {
        this.cell = cell;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.cell.setActor(this);
    }

    abstract void attack(Actor enemy);

    abstract void die(Cell cell);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
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
}
