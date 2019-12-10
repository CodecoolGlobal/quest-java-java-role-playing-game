package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.environment.OpenDoor;
import com.codecool.quest.logic.environment.Remains;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends Actor implements Moveable {
    private List<Item> inventory = new ArrayList<>();

    public Player(Cell cell) {
        super(cell, 20, 5, 0);
    }

    public String getTileName() {
        List<String> strInventory = new ArrayList<>();

        for (Item item : getInventory()) {
            strInventory.add(item.getTileName());
        }

        if (strInventory.contains("shield") && strInventory.contains("helm")) {
            return "playerWithShieldAndHelm";
        } else if (strInventory.contains("shield")) {
            return "playerWithShield";
        } else if (strInventory.contains("helm")) {
            return "playerWithHelm";
        }
        return "player";
    }

    public void visionRadius() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                cell.getNeighbor(i, j).setFog(null);
            }
        }
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (Objects.isNull(nextCell.getActor()) && !nextCell.getTileName().equals("wall")) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            visionRadius();
        } else if (!Objects.isNull(nextCell.getActor())) {
            if (nextCell.getActor().getTileName().equals("closedDoor")) {
                openDoor(nextCell);
            } else {
                battle(nextCell.getActor());
            }
        }
    }

    private void battle(Actor enemy) {
        enemy.health -= this.attack - enemy.defense;
        if (enemy.health > 0) {
            if(enemy.attack - this.defense > 0) {
                this.health -= enemy.attack - this.defense;
            }
        } else {
            enemy.getCell().setActor(null);
            enemy.isDead = true;
            enemy.getCell().setEnvironment(new Remains(enemy.getCell()));
            if(enemy.getTileName().equals("ogre")){
                enemy.getCell().setActor(null);
                enemy.getCell().setItem(new Key(enemy.getCell()));
            }
        }

        if (this.health <= 0) {
            System.out.println("GAME OVER");
        }
    }

    private void openDoor(Cell cell) {
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
                if (cell.getItem().getDefenseAmount() > 0) {
                    this.defense += cell.getItem().getDefenseAmount();
                    inventory.add(cell.getItem());
                } else {
                    inventory.add(cell.getItem());
                }
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

    public List<Item> getInventory() {
        return inventory;
    }

}
