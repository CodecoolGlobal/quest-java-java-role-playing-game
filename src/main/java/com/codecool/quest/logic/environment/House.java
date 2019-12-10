package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class House extends Environment {

    public House(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "house";
    }
}

