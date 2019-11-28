package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class TreeTrunk extends Environment {

    public TreeTrunk(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "treeTrunk";
    }
}
