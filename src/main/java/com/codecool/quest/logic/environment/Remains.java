package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Remains extends Environment {

    public Remains(Cell cell){
        super(cell);
    }
    public String getTileName() {
        return "monsterRemains";
    }
}
