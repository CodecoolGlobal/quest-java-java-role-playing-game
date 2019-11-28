package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class StoneFloor extends Environment {

    public StoneFloor(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "stoneFloor";
    }
}
