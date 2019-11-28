package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Sign extends Environment {

    public Sign(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "sign";
    }
}
