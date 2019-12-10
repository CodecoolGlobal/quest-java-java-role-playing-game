package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Fog extends Environment {
    public Fog(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "fog";
    }
}
