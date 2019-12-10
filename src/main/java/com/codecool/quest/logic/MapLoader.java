package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Door;
import com.codecool.quest.logic.actors.Ogre;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.environment.*;
import com.codecool.quest.logic.items.Apple;
import com.codecool.quest.logic.items.Helm;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.items.Shield;


import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class MapLoader {
    public static LinkedList<Skeleton> skeletons = new LinkedList<>();

    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();


        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    cell.setFog(new Fog(cell));
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'I':
                            cell.setType(CellType.GRASSONE);
                            break;
                        case 'i':
                            cell.setType(CellType.GRASSTWO);
                            break;
                        case 'T':
                            cell.setType(CellType.TREES);
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        case '|':
                            cell.setType(CellType.TREETRUNK);
                            break;
                        case 'W':
                            cell.setType(CellType.WALLDOWN);
                            break;
                        case ',':
                            cell.setType(CellType.WALLLEFTDOWNCORNER);
                            break;
                        case 'l':
                            cell.setType(CellType.WALLLEFT);
                            break;
                        case '"':
                            cell.setType(CellType.WALLLEFTTOPCORNER);
                            break;
                        case 'M':
                            cell.setType(CellType.WALLUP);
                            break;
                        case '$':
                            cell.setType(CellType.STONEFLOOR);
                            break;
                        case 'b':
                            cell.setType(CellType.BRIDGE);
                            break;
                        case '~':
                            cell.setType(CellType.WATER);
                            break;
                        case '*':
                            cell.setType(CellType.BONFIRE);
                            break;
                        case 'h':
                            cell.setType(CellType.HOUSE);
                            break;
                        case 'v':
                            cell.setType(CellType.BOAT);
                            break;
                        case 'n':
                            cell.setType(CellType.SIGN);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            Skeleton skeleton = new Skeleton(cell);
                            cell.setActor(skeleton);
                            skeletons.add(skeleton);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        case 'D':
                            cell.setType(CellType.FLOOR);
                            new Door(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Apple(cell);
                            break;
                        case 'H':
                            cell.setType(CellType.FLOOR);
                            new Helm(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            cell.setActor(new Ogre(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
