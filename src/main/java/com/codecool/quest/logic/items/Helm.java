package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Helm extends Item{
    public Helm(Cell cell) {
        super(cell, 0);
    }

    @Override
    public String getTileName() {
        return "helm";
    }
}
