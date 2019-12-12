package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Gandalf;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.environment.Fireball;
import com.codecool.quest.logic.items.Item;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label defenseLabel = new Label();
    ListView inventory = new ListView();
    List<Item> savedInventory = new ArrayList<>();
    int savedHealth;
    int savedDefense;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(10));

        ui.add(healthLabel, 0, 0);
        ui.add(defenseLabel, 0, 1);
        ui.add(new Label("Inventory"), 0, 2);
        ui.add(inventory, 0, 3);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        if (!MapLoader.currentMap.equals("/welcome.txt")) {
            map.getPlayer().visionRadius();
        }
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                map.getPlayer().move(0, -1);
                refresh();
                labelRefresh();
                break;
            case S:
                map.getPlayer().move(0, 1);
                refresh();
                labelRefresh();
                break;
            case A:
                map.getPlayer().move(-1, 0);
                refresh();
                labelRefresh();
                break;
            case D:
                map.getPlayer().move(1, 0);
                refresh();
                labelRefresh();
                break;
            case F:
                if (MapLoader.currentMap.equals("/welcome.txt")) {
                    changeMap("/map.txt");
                } else {
                    map.getPlayer().pickUp();
                    refresh();
                    labelRefresh();
                }
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
                if (cell.getFog() != null) {
                    Tiles.drawTile(context, cell.getFog(), x, y);
                }
                if (MapLoader.currentMap.equals("/map.txt") && map.getPlayer().getX() == 5 && map.getPlayer().getY() == 17) { // x: 22, y: 16
                    changeMap("/map_2.txt");
                }
                if (!MapLoader.currentMap.equals("/welcome.txt") && map.getPlayer().isDead()) {
                    System.out.println("death started");
                    changeMap("/death.txt");
                }
            }
        }
    }

    private void labelRefresh() {
        healthLabel.setText("Health: " + map.getPlayer().getHealth());
        defenseLabel.setText("Defense: " + map.getPlayer().getDefense());
        inventory.getItems().clear();
        for (Item item : map.getPlayer().getInventory()) {
            if (!inventory.getItems().contains(item.getTileName())) {
                inventory.getItems().add(item.getTileName());
            }
        }
    }

    private void aiMovement() {
        while (MapLoader.currentMap.equals("/map.txt") && !map.getPlayer().isDead()) {
            int playerX = map.getPlayer().getX();
            int playerY = map.getPlayer().getY();
            for (Skeleton skeleton : MapLoader.skeletons) {
                if (!skeleton.isDead() && skeleton.isAggroStatus()) {
                    int monsterX = skeleton.getX();
                    int monsterY = skeleton.getY();
                    skeleton.move(skeleton.calculateCoordinate(playerX, monsterX), skeleton.calculateCoordinate(playerY, monsterY));
                } else if (!skeleton.isDead()) {
                    skeleton.aggro();
                    skeleton.move(getRandomNumber(), getRandomNumber());
                }
            }
            //ogre movement
            int ogreX = MapLoader.ogre.getX();
            int ogreY = MapLoader.ogre.getY();
            MapLoader.ogre.move(MapLoader.ogre.calculateCoordinate(playerX, ogreX), MapLoader.ogre.calculateCoordinate(playerY, ogreY));

            int stealerX = MapLoader.stealer.getX();
            int stealerY = MapLoader.stealer.getY();
            MapLoader.stealer.move(MapLoader.stealer.calculateCoordinate(playerX, stealerX), MapLoader.stealer.calculateCoordinate(playerY, stealerY));

            refresh();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gandalfMovement();
    }

    private int getRandomNumber() {
        Random r = new Random();
        return r.nextInt(3) - 1;
    }

    private void changeMap(String newMap) {
        if (MapLoader.currentMap.equals("/welcome.txt")) {
            savedHealth = 20;
            savedDefense = 0;
        } else {
            savedInventory.addAll(map.getPlayer().getInventory());
            savedHealth = map.getPlayer().getHealth();
            savedDefense = map.getPlayer().getDefense();
        }
        MapLoader.currentMap = newMap;
        map = MapLoader.loadMap();
        map.getPlayer().getInventory().addAll(savedInventory);
        map.getPlayer().setHealth(savedHealth);
        map.getPlayer().setDefense(savedDefense);
        refresh();
        labelRefresh();
        if (MapLoader.currentMap.equals("/map.txt")) {
            map.getPlayer().visionRadius();
            CompletableFuture.runAsync(this::aiMovement);
        }
    }

    private void gandalfMovement() {
        int counter = 0;
        int dx = 1;
        while (!MapLoader.gandalf.isDead()) {
            if (!MapLoader.gandalf.getCell().getNeighbor(dx, 0).getType().isSteppable()) {
                dx = -dx;
            }
            if (counter == 5) {
                Gandalf gandalf = MapLoader.gandalf;
                Fireball ball = new Fireball(MapLoader.gandalf.getCell().getNeighbor(0, 1));
                while (!ball.isDead()) {
                    ball.move(ball.calculateCoordinate(map.getPlayer().getX(), ball.getX()), ball.calculateCoordinate(map.getPlayer().getY(), ball.getY()));
                    refresh();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (counter == 10) {

                ArrayList<Fireball> fireWall = new ArrayList<Fireball>();

                for (int i = 0; i < map.getWidth(); i++) {
                    Cell cell = map.getCell(i, 0);
                    Fireball ball = new Fireball(cell);
                    cell.setActor(ball);
                    fireWall.add(ball);
                }

                for (int i = 0; i < map.getHeight(); i++) {
                    for (Fireball k : fireWall) {
                        k.move(0, 1);
                    }
                    refresh();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                counter = 0;

            }

        MapLoader.gandalf.move(dx, 0);
        refresh();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++counter;
    }
    }
}
