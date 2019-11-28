package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Boat extends Environment {

    public Boat(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "boat";
    }
}
