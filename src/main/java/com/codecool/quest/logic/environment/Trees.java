package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Trees extends Environment {

    public Trees(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "trees";
    }
}