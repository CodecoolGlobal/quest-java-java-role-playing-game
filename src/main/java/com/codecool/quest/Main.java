package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.items.Item;
import javafx.animation.FadeTransition;
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
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    ListView inventory = new ListView();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(10));

        ui.add(healthLabel, 0, 0);
        ui.add(new Label("Inventory"), 0, 1);
        ui.add(inventory, 0, 2);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        CompletableFuture.runAsync(this::aiMovement);

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
                map.getPlayer().pickUp();
                refresh();
                labelRefresh();
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
                } else if (cell.getEnvironment() != null && Objects.isNull(cell.getItem())) {
                    Tiles.drawTile(context, cell.getEnvironment(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }

    }

    private void labelRefresh(){
        healthLabel.setText("Health: " + map.getPlayer().getHealth());
        inventory.getItems().clear();
        for (Item item : map.getPlayer().getInventory()) {
            if (!inventory.getItems().contains(item.getTileName())) {
                inventory.getItems().add(item.getTileName());
            }
        }
    }

    private void aiMovement(){
        while(true) {
            ListIterator<Skeleton> list_Iter = MapLoader.skeletons.listIterator(0);
            while (list_Iter.hasNext()){
                list_Iter.next().monsterMove(getRandomNumber(), getRandomNumber());
                refresh();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getRandomNumber(){
        Random r = new Random();
        return r.nextInt(3)-1;
    }
}