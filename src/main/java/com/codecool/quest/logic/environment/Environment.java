package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Environment implements Drawable {
    private Cell cell;

    public Environment(Cell cell) {
        this.cell = cell;
        this.cell.setEnvironment(this);
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
