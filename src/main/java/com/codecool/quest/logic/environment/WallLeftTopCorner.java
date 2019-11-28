package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class WallLeftTopCorner extends Environment {

    public WallLeftTopCorner(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "wallLeftTopCorner";
    }
}
