package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private int healingAmount;
    private int defenseAmount;

    public Item(Cell cell, int healingAmount, int defenseAmount) {
        this.healingAmount = healingAmount;
        this.defenseAmount = defenseAmount;
        this.cell = cell;
        this.cell.setItem(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public int getDefenseAmount() {
        return defenseAmount;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
