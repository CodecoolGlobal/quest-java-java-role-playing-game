package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Door extends Actor {

    public Door(Cell cell){
        super(cell,0,0,0);
    }

    public String getTileName() {
        return "closedDoor";
    }

}
