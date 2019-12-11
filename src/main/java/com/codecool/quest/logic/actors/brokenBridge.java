package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class brokenBridge extends Actor{

    public brokenBridge(Cell cell){
        super(cell,0,0,0);
    }

    public String getTileName() {
        return "brokenBridge";
    }

    @Override
    void die(Cell cell) {
        cell.setActor(null);
    }

    @Override
    void attack(Actor enemy) {

    }
}


