package uet.oop.bomberman;

import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.enemies.Ballom;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.enemies.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.Set;

public class createMap {
    public static char[][] grid = new char[Settings.MAX_ROW][Settings.MAX_COL];

    public static void importData(char[][] arr, int stage) throws IOException {
        String path = "res/levels/level" + stage + ".txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;

        for (int i = 0; i < Settings.MAX_ROW; i++) {
            line = bufferedReader.readLine();
            for (int j = 0; j < Settings.MAX_COL; j++) {
                arr[i][j] = line.charAt(j);
            }
        }
        System.out.println();
        bufferedReader.close();
    }
    public static void createMapLevel(int level) throws IOException {
        EntityList.clearList();
        switch (level) {
            case 1:
                //import tileset to array
//                char[][] arr = new char[Settings.MAX_ROW][Settings.MAX_COL];
                importData(grid, level);
                //map render
                for (int i = 0; i < Settings.MAX_ROW; i++) {
                    for (int j = 0; j < Settings.MAX_COL; j++) {
                        switch (grid[i][j]) {
                            case '6':
                                EntityList.walls.add(new Wall(j, i, Sprite.wall.getFxImage()));
                                break;
                            case '7':
                                EntityList.grasses.add((new Grass(j, i, Sprite.grass.getFxImage())));
                                break;
                            case '8':
                                EntityList.bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                                break;
                            default:
                                break;
                        }
                    }
                }
                EntityList.enemies.add(new Ballom(15, 10, Sprite.balloom_left1.getFxImage(), 1, 0, Enemy.enemyDir.UP));
                EntityList.enemies.add(new Oneal(5, 7, Sprite.oneal_left1.getFxImage(), 1, 100, Enemy.enemyDir.UP));
                break;

            case 2:
                break;
        }
//        System.out.println(grid[1][8]);
    }
}
