package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Bonfire extends Environment {

    public Bonfire(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "bonfire";
    }
}