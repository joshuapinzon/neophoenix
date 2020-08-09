package model.enemyMissileOne;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;

import java.awt.*;
import java.util.ArrayList;

public class EnemyMissileOne extends GameFigure implements Subject {

    public static int UNIT_MOVE = 5;
    public static int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING = 0;
    public static final int STATE_FALLING = 1;
    public static final int STATE_DONE = 2;

    int size = 40;
    int width, height;
    boolean movingRight = true;
    boolean movingDown = true;
    int state;
    Color color;
    Color color_2;
    EnemyMissileOneAnimStrategy animStrategy;

    ArrayList<Observer> listeners = new ArrayList<>();

    public EnemyMissileOne(int x, int y) {
        super(x, y);
        width = size;
        height = size;
        state = STATE_FLYING;
        color = Color.RED;
        color_2 = Color.RED;
        animStrategy = new EnemyMissileOneAnimFlying(this);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setStroke(new BasicStroke(1)); //thickness of the line
        g2.setColor(color);
        g2.fillOval((int)location.x - width, (int)location.y - height, width, height);
    }

    @Override
    public void update() {
        updateState();
        animStrategy.animate(); //Strategy design pattern
    }

    private void updateState() {
        if (state == STATE_FLYING) {
            if (hitCount > 0) {
                state = STATE_FALLING;
                animStrategy = new EnemyMissileOneAnimFalling(this);
            }
        } else if (state == STATE_FALLING) {
            if (location.y >= Main.win.canvas.height) {
                state = STATE_DONE;
            }
        } else if (state == STATE_DONE) {
            super.done = true;
            notifyEvent(); // UFO died!
        }
    }

    @Override
    public int getCollisionRadius() { return (int)(size / 2 * 0.75);
    }

    @Override
    public void attachListener(Observer o) {
        listeners.add(o);
    }

    @Override
    public void detachListener(Observer o) {
        listeners.remove(o);
    }

    @Override
    public void notifyEvent() {
        for (var o: listeners) {
            o.eventReceived();
        }
    }
}
