package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Ogre extends Actor {
    public Ogre(Cell cell) {
        super(cell, 20, 7, 0);
    }

    @Override
    public String getTileName() {
        return "ogre";
    }

}