package com.codecool.quest.logic.environment;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public class Fog implements Drawable {

    public Fog(Cell cell){}

    public String getTileName() {
        return "fog";
    }
}
