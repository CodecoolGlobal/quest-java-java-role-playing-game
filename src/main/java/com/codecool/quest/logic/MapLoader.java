package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.*;
import com.codecool.quest.logic.environment.*;
import com.codecool.quest.logic.items.*;


import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class MapLoader {
    public static LinkedList<Skeleton> skeletons = new LinkedList<>();
    public static Ogre ogre;
    public static Gandalf gandalf;
    public static String currentMap = "/welcome.txt";

    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream(currentMap);
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
                    if (currentMap.equals("/map.txt")) {
                        //cell.setFog(new Fog(cell));
                    }
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
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.T);
                            } else {
                                cell.setType(CellType.TREE);
                            }
                            break;
                        case '|':
                            cell.setType(CellType.TREETRUNK);
                            break;
                        case 'W':
                            cell.setType(CellType.WALLDOWN);
                            break;
                        case 'w':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.W);
                            }
                            break;
                        case 'e':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.E);
                            }
                            break;
                        case 'f':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.F);
                            }
                            break;
                        case '!':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.EXCLAMATION);
                            }
                            break;
                        case ',':
                            cell.setType(CellType.WALLLEFTDOWNCORNER);
                            break;
                        case 'l':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.L);
                            } else {
                                cell.setType(CellType.WALLLEFT);
                            }
                            break;
                        case '"':
                            cell.setType(CellType.WALLLEFTTOPCORNER);
                            break;
                        case 'M':
                            cell.setType(CellType.WALLUP);
                            break;
                        case 'm':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.M);
                            }
                            break;
                        case '[':
                            cell.setType(CellType.WALLRIGHT);
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
                        case 'Y':
                            cell.setType(CellType.CANDLESTAND);
                            break;
                        case 'n':
                            cell.setType(CellType.SIGN);
                            break;
                        case '`':
                            cell.setType(CellType.WALLRIGHTTOPCORNER);
                            break;
                        case ';':
                            cell.setType(CellType.WALLRIGHTDOWNCORNER);
                            break;
                        case '1':
                            cell.setType(CellType.COLUMNTOPLEFT);
                            break;
                        case '2':
                            cell.setType(CellType.COLUMNBOTTOMLEFT);
                            break;
                        case '3':
                            cell.setType(CellType.COLUMNTOPRIGHT);
                            break;
                        case '4':
                            cell.setType(CellType.COLUMNBOTTOMRIGHT);
                            break;
                        case 'c':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.C);
                            } else {
                                cell.setType(CellType.SMALLCANDLESTAND);
                            }
                            break;
                        case 's':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.S);
                            } else {
                                cell.setType(CellType.FLOOR);
                                Skeleton skeleton = new Skeleton(cell);
                                cell.setActor(skeleton);
                                skeletons.add(skeleton);
                            }
                            break;
                        case '@':
                            if(currentMap.equals("/map.txt")) {
                                cell.setType(CellType.FLOOR);
                            } else {
                                cell.setType(CellType.STONEFLOOR);
                            }
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
                        case 'd':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.D);
                            }
                            break;
                        case 'p':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.P);
                            }
                            break;
                        case 'q':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.Q);
                            }
                            break;
                        case 'u':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.U);
                            }
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'a':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.A);
                            } else {
                                cell.setType(CellType.FLOOR);
                                new Apple(cell);
                            }
                            break;
                        case 'H':
                            cell.setType(CellType.FLOOR);
                            new Helm(cell);
                            break;
                        case 'o':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.O);
                            } else {
                                cell.setType(CellType.FLOOR);
                                Ogre keyKeeper = new Ogre(cell);
                                cell.setActor(keyKeeper);
                                ogre = keyKeeper;
                            }
                            break;
                        case 'B':
                            cell.setType(CellType.BRIDGE);
                            new brokenBridge(cell);
                            break;
                        case 'r':
                            if(currentMap.equals("/welcome.txt")) {
                                cell.setType(CellType.R);
                            } else {
                                cell.setType(CellType.FLOOR);
                                new Tool(cell);
                            }
                            break;
                        case 'G':
                            cell.setType(CellType.STONEFLOOR);
                            Gandalf mage = new Gandalf(cell);
                            cell.setActor(mage);
                            gandalf = mage;
                            break;
                        case 'ŧ':
                            cell.setType(CellType.FLOOR);
                            new Stealer(cell);
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
