package com.codecool.quest.logic.actors;

public interface Aggro {

    int calculateCoordinate(int playerCoordinate, int monsterCoordinate);

    void aggro();

    void move(int dx, int dy);
}
