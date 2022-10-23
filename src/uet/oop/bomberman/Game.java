package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.GUI.Menu;
import uet.oop.bomberman.GUI.Menubutton;
import uet.oop.bomberman.GUI.audioScroller;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.*;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;


public class Game extends Application {
    public static EntityList entityList = new EntityList();

    ImageView nextlevel;
    ImageView gameover;
    public static ImageView startscreenView;

    private static int level = 2;
    public static String gamestate = " ";
    private static GraphicsContext gc;
    private static Canvas canvas;

    public static long time = 0;
    public static Font font = Font.loadFont("file:res/font/BOMBERMA.TTF", 19);
    public static final String style = "style.css";
/*

/*
    public static long time = 120;
*/
    public static long delaytime = 100;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int lv) {
        level = lv;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);

        stage.setTitle("BomberMan");
        Image icon = new Image("images/icon.png");
        stage.getIcons().add(icon);

        // Tao Canvas
        canvas = new Canvas(Settings.WIDTH, Settings.HEIGHT);
        canvas.setLayoutY(30);
        gc = canvas.getGraphicsContext2D();

        Image startscreen = new Image("images/author1.png");

        startscreenView = new ImageView(startscreen);
        startscreenView.setX(0);
        startscreenView.setY(0);
        startscreenView.setFitHeight(Settings.HEIGHT);
        startscreenView.setFitWidth(Settings.WIDTH);

        //effect
        Glow glow = new Glow();
        glow.setLevel(0.9);

        // Tao root container
        Group root = new Group();
        Menu.createMenu(root);
        root.getChildren().add(canvas);
        root.getChildren().add(startscreenView);

        Menu.createButton(root);
        audioScroller.slider(root);
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
        scene.setFill(Color.WHITE);


        new SoundManager("sound/start.wav", "title");
        SoundManager.updateSound();

        gamestate = "startmenu";
        AnimationTimer timer = new AnimationTimer() {
            long start = System.currentTimeMillis();
            long before = 0;

            @Override
            public void handle(long l) {
                if (gamestate.equals("startmenu")) {
                    SoundManager.updateSound();
                    Menubutton.update();
                }

                if (gamestate.equals("running")) {
                    render();
                    update();
                    long end = (System.currentTimeMillis() - start) / 1000;
                    if (end - before == 1) Game.time++;
                    before = end;
                }
                if (gamestate.equals("pause")) {
                    startscreenView.setVisible(true);
                    Menubutton.update();
                    SoundManager.updateSound();
                }

/*
                if (gamestate.equals("gameover")) {
                    gamestate = "startmenu";
                }

                /*
*/
                if (gamestate.equals("gameover")) {
                    //show gameover trong muc images + time
                    gameover.setVisible(true);
                    //reset game hoac show menu
                    if (delaytime > 0) {
                        delaytime--;
                        System.out.println(delaytime);
                    } else {
                        delaytime = 100;
                        gamestate = "startmenu";
                    }
                }
                if (gamestate.equals("nextLevel")) {
                    //tao level
                    nextlevel.setVisible(true);
                    SoundManager.updateSound();
                    if (delaytime > 0) delaytime--;
                    else {
                        delaytime = 100;
                        nextlevel.setVisible(false);
                        level++;
                        CreateMap.createMapLevel(level);
                        gamestate = "running";
                    }
                }
            }
        };

        timer.start();

        // update bomberman position
        scene.setOnKeyPressed(event -> {
/*
            if (event.getCode().toString().equals("ESCAPE")) {
                if (gamestate.equals("running")) {
                    gamestate = "pause";
                } else if (gamestate.equals("pause")) {
                    gamestate = "running";
                }
            }
            if (gamestate.equals("running")) {
                if (event.getCode().toString().equals("UP") ||
                        event.getCode().toString().equals("W")) {
                    bomberman.goUp();
                } else if (event.getCode().toString().equals("DOWN") ||
                        event.getCode().toString().equals("S")) {
                    bomberman.goDown();
                } else if (event.getCode().toString().equals("LEFT") ||
                        event.getCode().toString().equals("A")) {
                    bomberman.goLeft();
                } else if (event.getCode().toString().equals("RIGHT") ||
                        event.getCode().toString().equals("D")) {
                    bomberman.goRight();
                } else if (event.getCode().toString().equals("SPACE") && bomberman.bombs.isEmpty()) {
                    bomberman.placeBomb();
                }
//  aaa
            if (event.getCode().toString().equals("UP")) {
                entityList.getBomberman().setImg(Sprite.player_up.getFxImage());
                entityList.getBomberman().goUp();
            } else if (event.getCode().toString().equals("DOWN")) {
                entityList.getBomberman().setImg(Sprite.player_down.getFxImage());
                entityList.getBomberman().goDown();
            } else if (event.getCode().toString().equals("LEFT")) {
                entityList.getBomberman().setImg(Sprite.player_left.getFxImage());
                entityList.getBomberman().goLeft();
            } else if (event.getCode().toString().equals("RIGHT")) {
                entityList.getBomberman().setImg(Sprite.player_right.getFxImage());
                entityList.getBomberman().goRight();
            } else if (event.getCode().toString().equals("SPACE") && entityList.getBombs().isEmpty()) {
                entityList.getBomberman().setBomb();
*/
            }
        });
        //update bomberman sprites
        scene.setOnKeyReleased(event -> {
/*
            if (gamestate.equals("running")) {
                if (event.getCode().toString().equals("LEFT")) {
                    bomberman.setImg(Sprite.player_left.getFxImage());
                } else if (event.getCode().toString().equals("UP")) {
                    bomberman.setImg(Sprite.player_up.getFxImage());
                } else if (event.getCode().toString().equals("RIGHT")) {
                    bomberman.setImg(Sprite.player_right.getFxImage());
                } else if (event.getCode().toString().equals("DOWN")) {
                    bomberman.setImg(Sprite.player_down.getFxImage());
                } else if (event.getCode().toString().equals("C")) {
                    for (int i = 0; i < EntityList.enemies.size(); i++) EntityList.enemies.get(i).setAlive(false);
                }
*/
            if (event.getCode().toString().equals("LEFT")) {
                entityList.getBomberman().setImg(Sprite.player_left.getFxImage());
            } else if (event.getCode().toString().equals("UP")) {
                entityList.getBomberman().setImg(Sprite.player_up.getFxImage());
            } else if (event.getCode().toString().equals("RIGHT")) {
                entityList.getBomberman().setImg(Sprite.player_right.getFxImage());
            } else if (event.getCode().toString().equals("DOWN")) {
                entityList.getBomberman().setImg(Sprite.player_down.getFxImage());
            } else if (event.getCode().toString().equals("C")) {
                for (int i = 0; i < entityList.getEnemies().size(); i++) entityList.getEnemies().get(i).setAlive(false);

            }
        });

    }

    public void update() {
        Menubutton.update();
        SoundManager.updateSound();
        Menu.updateMenu();
        for (Wall wall : entityList.getWalls()) wall.update();
        for (Bomb bomb : entityList.getBombs()) bomb.update();
        for (int i = 0; i < entityList.getBricks().size(); i++) entityList.getBricks().get(i).update();
        for (int i = 0; i < entityList.getItems().size(); i++) entityList.getItems().get(i).update();
        for (int i = 0; i < entityList.getEnemies().size(); i++) entityList.getEnemies().get(i).update();
        entityList.getPortal().update();
        entityList.getBomberman().update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Grass grass : entityList.getGrasses()) grass.render(gc);
        for (Wall wall : entityList.getWalls()) wall.render(gc);
        for (Bomb bomb : entityList.getBombs()) {
            bomb.render(gc);
            if (bomb.isFire()) {
                bomb.explode();
                for (Flame flame : entityList.getFlames()) flame.render(gc);
            }
        }
        for (Item item : entityList.getItems()) item.render(gc);
        entityList.getPortal().render(gc);
        for (Brick brick : entityList.getBricks()) brick.render(gc);
        for (Enemy enemy : entityList.getEnemies()) enemy.render(gc);
        entityList.getBomberman().render(gc);
    }

/*
    public void showMenu() {
        startscreenView.setVisible(true);
        if (checksetting) {
            playbutton.setVisible(true);
        }
        if (checkplay) {
            settings.setVisible(true);
        }
        continueGame.setVisible(false);
        homeScreen.setVisible(false);
        gameover.setVisible(false);
    }

    public void hideMenu() {
        startscreenView.setVisible(false);
        playbutton.setVisible(false);
        settings.setVisible(false);
        gamemodeEasy.setVisible(false);
        gamemodeMedium.setVisible(false);
        gamemodeHard.setVisible(false);
        SoundManager.updateSound();
        new SoundManager("sound/pacbaby.wav", "ingame");
    }
*/

    public static void moveCamera(int x, int y) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.translate(-x, -y);
    }
}
