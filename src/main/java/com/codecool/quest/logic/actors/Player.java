package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Shield;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell, 20, 5, 0);
    }

    public String getTileName() {
        List<String> strInventory = new ArrayList<>();

        for (Item item : getInventory()) {
            strInventory.add(item.getTileName());
        }
        return "player";
    }
}
