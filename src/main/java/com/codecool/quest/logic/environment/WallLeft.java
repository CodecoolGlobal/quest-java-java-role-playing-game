package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class WallLeft extends Environment {

    public WallLeft(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "wallLeft";
    }
}
