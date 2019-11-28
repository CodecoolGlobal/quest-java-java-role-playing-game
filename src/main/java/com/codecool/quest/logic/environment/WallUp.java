package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class WallUp extends Environment {

    public WallUp(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "wallUp";
    }
}
