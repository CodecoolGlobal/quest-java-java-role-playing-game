package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class WallDown extends Environment {

    public WallDown(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "wallDown";
    }
}
