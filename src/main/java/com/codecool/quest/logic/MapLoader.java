package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Door;
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
                            cell.setType(CellType.FLOOR);
                            new GrassOne(cell);
                            break;
                        case 'i':
                            cell.setType(CellType.FLOOR);
                            new GrassTwo(cell);
                            break;
                        case 'T':
                            cell.setType(CellType.WALL);
                            new Trees(cell);
                            break;
                        case 't':
                            cell.setType(CellType.WALL);
                            new Tree(cell);
                            break;
                        case '|':
                            cell.setType(CellType.WALL);
                            new TreeTrunk(cell);
                            break;
                        case 'W':
                            cell.setType(CellType.WALL);
                            new WallDown(cell);
                            break;
                        case ',':
                            cell.setType(CellType.WALL);
                            new WallLeftDownCorner(cell);
                            break;
                        case 'l':
                            cell.setType(CellType.WALL);
                            new WallLeft(cell);
                            break;
                        case '"':
                            cell.setType(CellType.WALL);
                            new WallLeftTopCorner(cell);
                            break;
                        case 'M':
                            cell.setType(CellType.WALL);
                            new WallUp(cell);
                            break;
                        case '$':
                            cell.setType(CellType.FLOOR);
                            new StoneFloor(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Bridge(cell);
                            break;
                        case '~':
                            cell.setType(CellType.WALL);
                            new Water(cell);
                            break;
                        case '*':
                            cell.setType(CellType.WALL);
                            new Bonfire(cell);
                            break;
                        case 'n':
                            cell.setType(CellType.WALL);
                            new Sign(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.WALL);
                            new House(cell);
                            break;
                        case 'v':
                            cell.setType(CellType.WALL);
                            new Boat(cell);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            Skeleton skeleton = new Skeleton(cell);
                            map.setSkeleton(skeleton);
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
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
