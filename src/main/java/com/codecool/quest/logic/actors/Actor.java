package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.environment.Environment;
import com.codecool.quest.logic.environment.OpenDoor;
import com.codecool.quest.logic.environment.Remains;
import com.codecool.quest.logic.items.Apple;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Actor implements Drawable {
    Cell cell;
    int health;
    final int originalHealth = 20;
    int attack;
    int defense;
    boolean isDead = false;

    Actor(Cell cell, int health, int attack, int defense) {
        this.cell = cell;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.cell.setActor(this);
    }

    public int getHealth() {
        return health;
    }

    public boolean isDead() {
        return isDead;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

}
