package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Bridge extends Environment {

    public Bridge(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "bridge";
    }
}

