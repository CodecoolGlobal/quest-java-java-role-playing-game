package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
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
        tileMap.put("closedDoor", new Tile(3,9));
        tileMap.put("openDoor", new Tile(6,9));
        tileMap.put("monsterRemains", new Tile(0,15));
        //Actors
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("playerWithShield", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        //Items
        tileMap.put("shield", new Tile(5, 26));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("apple", new Tile(15,29));
        tileMap.put("helm", new Tile(4, 22));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
