package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class GrassOne extends Environment {

    public GrassOne(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "grass1";
    }
}
