package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;

public class OpenDoor extends Environment {

    public OpenDoor(Cell cell){
            super(cell);
    }
    public String getTileName() {
        return "openDoor";
    }
}
