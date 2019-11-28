package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.items.Shield;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell, 20, 5, 0);
    }

    public String getTileName() {
        for (Item item : getInventory()) {
            if (item instanceof Shield) {
                return "playerWithShield";
            }
        }

        return "player";
    }
}
