package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.MapLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        //Environment
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("grass1", new Tile(5, 0));
        tileMap.put("grass2", new Tile(0, 2));
        tileMap.put("trees", new Tile(3, 1));
        tileMap.put("tree", new Tile(4, 2));
        tileMap.put("treeTrunk", new Tile(20, 6));
        tileMap.put("closedDoor", new Tile(3,9));
        tileMap.put("openDoor", new Tile(6,9));
        tileMap.put("monsterRemains", new Tile(0,15));
        tileMap.put("bridge", new Tile(6,5));
        tileMap.put("brokenBridge", new Tile(17,5));
        tileMap.put("tool", new Tile(11,27));
        tileMap.put("water", new Tile(8,4));
        tileMap.put("wallDown", new Tile(19,2));
        tileMap.put("wallLeftDownCorner", new Tile(18,2));
        tileMap.put("wallRightDownCorner", new Tile(20,2));
        tileMap.put("wallLeft", new Tile(18,1));
        tileMap.put("wallRight", new Tile(20,1));
        tileMap.put("wallLeftTopCorner", new Tile(18,0));
        tileMap.put("wallRightTopCorner", new Tile(20,0));
        tileMap.put("wallUp", new Tile(19,0));
        tileMap.put("stoneFloor", new Tile(19,1));
        tileMap.put("bonfire", new Tile(14,10));
        tileMap.put("sign", new Tile(0,7));
        tileMap.put("house", new Tile(8,20));
        tileMap.put("boat", new Tile(9,19));
        tileMap.put("fog", new Tile(0,0));
        tileMap.put("candleStand", new Tile(5,15));
        tileMap.put("smallCandleStand", new Tile(4,15));
        tileMap.put("columnTopLeft", new Tile(20,3));
        tileMap.put("columnBottomLeft", new Tile(20,4));
        tileMap.put("columnTopRight", new Tile(21,3));
        tileMap.put("columnBottomRight", new Tile(21,4));
        //Actors
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("playerWithShield", new Tile(27, 0));
        tileMap.put("playerWithHelm", new Tile(30,0));
        tileMap.put("playerWithShieldAndHelm", new Tile(28,0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("ogre", new Tile(30,6));
        tileMap.put("gandalf", new Tile(24,1));
        tileMap.put("dyingGandalf", new Tile(31,5));
        //Items
        tileMap.put("shield", new Tile(5, 26));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("apple", new Tile(15,29));
        tileMap.put("helm", new Tile(4, 22));
        //Spells
        tileMap.put("fireball", new Tile(15, 10));
        tileMap.put("stealer", new Tile(26, 9));
        tileMap.put("spider", new Tile(30, 5));
        //Letters
        tileMap.put("w", new Tile(28, 31));
        tileMap.put("e", new Tile(23, 30));
        tileMap.put("l", new Tile(30, 30));
        tileMap.put("c", new Tile(21, 30));
        tileMap.put("o", new Tile(20, 31));
        tileMap.put("m", new Tile(31, 30));
        tileMap.put("t", new Tile(25, 31));
        tileMap.put("d", new Tile(22, 30));
        tileMap.put("q", new Tile(22, 31));
        tileMap.put("u", new Tile(26, 31));
        tileMap.put("s", new Tile(24, 31));
        tileMap.put("p", new Tile(21, 31));
        tileMap.put("r", new Tile(23, 31));
        tileMap.put("a", new Tile(19, 30));
        tileMap.put("f", new Tile(24, 30));
        tileMap.put("y", new Tile(30, 31));
        tileMap.put("i", new Tile(27, 30));
        tileMap.put("!", new Tile(19, 25));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
