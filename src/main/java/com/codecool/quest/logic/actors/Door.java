package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Key;

public class Door extends Actor {

    public Door(Cell cell){
        super(cell,0,0,0);
    }

    public String getTileName() {
        return "closedDoor";
    }

    @Override
    void die(Cell cell) {
        cell.setActor(null);
        cell.setType(CellType.OPENDOOR);
    }
}
