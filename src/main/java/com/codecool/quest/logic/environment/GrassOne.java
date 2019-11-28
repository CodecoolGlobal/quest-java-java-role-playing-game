package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Grass1 extends Environment {

    public Grass1(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "grass1";
    }
}
