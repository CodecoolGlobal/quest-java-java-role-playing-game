package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
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
    private Cell cell;
    private List<Item> inventory = new ArrayList<>();
    private int health;
    private final int originalHealth = 20;
    private int attack;
    private int defense;

    public Actor(Cell cell, int health, int attack, int defense) {
        this.cell = cell;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (Objects.isNull(nextCell.getActor()) && !nextCell.getTileName().equals("wall")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (!Objects.isNull(nextCell.getActor())) {
            if (nextCell.getActor().getTileName().equals("closedDoor")) {
                openDoor(nextCell);
            } else {
                battle(nextCell.getActor());
            }
        }
    }

    public void monsterMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (Objects.isNull(nextCell.getActor()) && !nextCell.getTileName().equals("wall")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public void battle(Actor enemy) {
        enemy.health -= this.attack - enemy.defense;
        if (enemy.health > 0) {
            this.health -= enemy.attack - this.defense;
        } else {
            enemy.getCell().setActor(null);
            MapLoader.skeletons.remove(enemy);
            enemy.getCell().setEnvironment(new Remains(enemy.getCell()));
        }

        if (this.health <= 0) {
            System.out.println("GAME OVER");
        }
    }

    public void openDoor(Cell cell) {
        for (Item item : inventory) {
            if (item instanceof Key) {
                cell.setActor(null);
                cell.setEnvironment(new OpenDoor(cell));
                inventory.remove(item);
                break;
            }
        }
    }

    public void pickUp() {
        if (cell.getItem() != null) {
            if (!isHealingItem(cell.getItem())) {
                inventory.add(cell.getItem());
            } else if (isHealingItem(cell.getItem()) && this.health < 20) { // need better health check, don't go above a number
                health = this.health + cell.getItem().getHealingAmount();
                if (health > originalHealth) {
                    health = originalHealth;
                }
            }
            cell.setItem(null);
        }
    }

    private boolean isHealingItem(Object cell) {
        Class<?> objectClass = cell.getClass();
        for (Field field : objectClass.getFields()) {
            if (field.getName().equals("healingItem")) {
                return true;
            }
        }
        return false;
    }

    public int getHealth() {
        return health;
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

    public List<Item> getInventory() {
        return inventory;
    }

}
