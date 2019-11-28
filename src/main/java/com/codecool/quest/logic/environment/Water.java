package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Water extends Environment {

    public Water(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "water";
    }
}
