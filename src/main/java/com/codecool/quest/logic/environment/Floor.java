package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Floor extends Environment {
    public Floor(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "floor";
    }
}
