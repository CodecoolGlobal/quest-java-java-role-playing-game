package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.items.Item;

public class Shield extends Item {
    public Shield(Cell cell) {
        super(cell, 0, 2);
    }

    @Override
    public String getTileName() {
        return "shield";
    }
}
