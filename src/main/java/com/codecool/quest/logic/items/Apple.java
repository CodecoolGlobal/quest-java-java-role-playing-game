package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Apple extends Item {
    public String healingItem;


    public Apple(Cell cell){
        super(cell, 2);
    }

    public String getTileName() {
        return "apple";
    }
}
