package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class WallLeftDownCorner extends Environment {

    public WallLeftDownCorner(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "wallLeftDownCorner";
    }
}
