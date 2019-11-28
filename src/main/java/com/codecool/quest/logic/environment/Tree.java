package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class Tree extends Environment {

    public Tree(Cell cell){
        super(cell);
    }

    public String getTileName() {
        return "tree";
    }
}
