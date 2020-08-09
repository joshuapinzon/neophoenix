package controller;

import controller.observer.UFOObserverAddNew;
import model.*;
import model.enemyMissileOne.EnemyMissileOne;
import model.vitalityBar.VitalityBar;
import model.dragon.Dragon;
import model.tracker.Tracker;
import model.ufo.UFO;
import view.MyWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static MyWindow win;
    public static GameData gameData;
    public static PlayerInputEventQueue playerInputEventQueue;
    public static boolean running = false;

    public static int INDEX_MOUSE_POINTER = 0; //in gameData.fixedObject
    public static int INDEX_SHOOTER = 1;

    public static int FPS = 30; // frames per second

    public static void main(String[] args) {

        win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        gameData = new GameData();
        playerInputEventQueue = new PlayerInputEventQueue();

        startScreen();

        initGame();

        gameLoop();

    }

    static void startScreen() {
        //show initial message on canvas
        Font font = new Font("Courier New",Font.ITALIC, 40);
        gameData.friendObject.add(new Text("NEOPHOENIX", 220, 100, Color.RED, font));
        Font font2 = new Font("Courier New", Font.BOLD, 20);
        gameData.friendObject.add(new Text("press start to play", 230, 400, Color.RED, font2));

        Font font3 = new Font("Courier New", Font.BOLD, 15);
        gameData.friendObject.add(new Text("Instructions:", 100, 180, Color.GREEN, font3));
        gameData.friendObject.add(new Text("Press Q, W or E for shooting", 100, 190, Color.GREEN, font3));
        gameData.friendObject.add(new Text("Each collision will affect your vitality level", 100, 200, Color.GREEN, font3));
        gameData.friendObject.add(new Text("This is so because every energy release weakens NeoPhoenix", 100, 210, Color.GREEN, font3));
        gameData.friendObject.add(new Text("Defeat all enemies without running out of vitality", 100, 220, Color.GREEN, font3));

        //gameData.friendObject.add(new Text("Your vitality", 330, 500, Color.RED, font3));

        while (!running) {
            Main.win.canvas.render();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // finish when running == true;
    }



    static void initGame() {
        gameData.clear();

        Font font = new Font("Courier New",Font.BOLD, 15);
        gameData.friendObject.add(new Text("Vitality level: ", 400, 400, Color.YELLOW, font));

        gameData.fixedObject.add(new MousePointer(0, 0));
        gameData.friendObject.add(new VitalityBar(400, 400));
        //gameData.friendObject.add(new Missile(50, 50));
        int x = Main.win.getWidth() / 2;
        int y = Main.win.getHeight() - 100;
        gameData.fixedObject.add(new Shooter(x, y));

        addUFOwithListener(100, 100);

        gameData.enemyObject.add(new UFO(100, 100));
        gameData.enemyObject.add(new UFO(200, 100));
        gameData.enemyObject.add(new UFO(300, 100));
        gameData.enemyObject.add(new UFO(400, 100));
        gameData.enemyObject.add(new UFO(500, 100));

        gameData.enemyObject.add(new UFO(100, 150));
        gameData.enemyObject.add(new UFO(200, 150));
        gameData.enemyObject.add(new UFO(300, 150));
        gameData.enemyObject.add(new UFO(400, 150));
        gameData.enemyObject.add(new UFO(500, 150));

        gameData.enemyObject.add(new UFO(100, 200));
        gameData.enemyObject.add(new UFO(200, 200));
        gameData.enemyObject.add(new UFO(300, 200));
        gameData.enemyObject.add(new UFO(400, 200));
        gameData.enemyObject.add(new UFO(500, 200));


        //gameData.enemyObject.add(new EnemyMissileOne(350, 10));


    }

    public static void addUFOwithListener(int x, int y) {
        var ufo = new UFO(x, y);
        ufo.attachListener(new UFOObserverAddNew());
        gameData.enemyObject.add(ufo);
    }

    public static int count = 0;
    public static int count2 = 0;

    static void gameLoop() {

        running = true;

        // game loop
        while (running) {
            long startTime = System.currentTimeMillis();

            playerInputEventQueue.processInputEvents();
            processCollisions();
            gameData.update();
            win.canvas.render();

            Random rand = new Random();
            int max = 300;
            int min = 100;
            int  n = rand.nextInt((max - min) + 1) + min;

            int max2 = 200;
            int min2 = 50;

            int max3 = 500;
            int min3 = 100;

            int  m = rand.nextInt((max2 - min2) + 1) + min2;
            int  p = rand.nextInt((max3 - min3) + 1) + min3;

            if (gameData.enemyObject.size() < 10) {
                if(m > 100) {
                    gameData.enemyObject.add(new UFO(100, n));
                } else {
                    gameData.enemyObject.add(new Tracker(p, n));
                }
                    count2++;
                    count++;

                if (count == 5) {
                    gameData.enemyObject.add(new Dragon(350, 10));
                    gameData.enemyObject.add(new EnemyMissileOne(350, 10));
                }

                if (count2 > 7) {
                    if(m > 100) {
                        gameData.enemyObject.add(new EnemyMissileOne(350, 10));
                    }
                }

            }


            long endTime = System.currentTimeMillis();

            long timeSpent = endTime - startTime;
            long sleepTime = (long)(1000.0 / FPS - timeSpent);

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static int i = 200;

    static void processCollisions() {
        var shooter = (Shooter) Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);
        for (var enemy: Main.gameData.enemyObject) {
            if (shooter.collideWith(enemy)) {
                ++shooter.hitCount;
                ++enemy.hitCount;
                Font font = new Font("Courier New",Font.BOLD, 15);
                String number = Integer.toString(i);
                if (i > 0) {
                    gameData.friendObject.remove(1);
                    gameData.friendObject.add(new Text(number, 600, 400, Color.YELLOW, font));
                } else {
                    gameData.friendObject.add(new Text("ZERO", 600, 400, Color.RED, font));
                }
                i--;
            }
            if (i == 0) {
                Font font = new Font("Courier New",Font.BOLD, 40);
                gameData.fixedObject.add(new Text("GAME OVER", 250, 200, Color.YELLOW, font));
                Font font2 = new Font("Courier New",Font.BOLD, 20);
                gameData.fixedObject.add(new Text("Please try again", 260, 300, Color.YELLOW, font2));
            }
        }

        for (var friend: Main.gameData.friendObject) {
            for (var enemy: Main.gameData.enemyObject) {
                if (friend.collideWith(enemy)) {
                    ++friend.hitCount;
                    ++enemy.hitCount;
                }
            }
        }

    }
}
