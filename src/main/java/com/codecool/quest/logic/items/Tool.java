package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;


public class Tool extends Item {

        public Tool(Cell cell){
            super(cell, 0, 0);
        }

        public String getTileName() {
            return "tool";
        }


}
