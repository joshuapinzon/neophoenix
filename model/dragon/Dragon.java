package model.dragon;

import controller.Main;
import controller.observer.Observer;
import controller.observer.Subject;
import model.GameFigure;
import model.Text;

import java.awt.*;
import java.util.ArrayList;

import static controller.Main.gameData;

public class Dragon extends GameFigure implements Subject {

    public static int UNIT_MOVE = 10;
    public static int UNIT_MOVE_FALLING = 5;
    public static final int STATE_FLYING = 0;
    public static final int STATE_FALLING = 1;
    public static final int STATE_DONE = 2;


    int size = 40;
    int width, height;
    int state;
    boolean movingRight = true;
    Color color;
    Color color_2;
    Color color_3;
    DragonAnimStrategy animStrategy;

    ArrayList<Observer> listeners = new ArrayList<>();

    public Dragon(int x, int y) {
        super(x, y);
        width = size;
        height = size / 2;
        state = STATE_FLYING;
        color = Color.GREEN;
        color_2 = Color.RED;
        color_3 = Color.WHITE;
        animStrategy = new DragonAnimFlying(this);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setStroke(new BasicStroke(1)); //thickness of the line
        g2.setColor(color);

        g2.fillRect((int)location.x - width /2, (int)location.y - height / 2, width * 2, height * 4);

        //eyes
        g2.setColor(color_3);
        g2.fillRect((int)location.x - width /2, (int)location.y - 5, width - 15, height);
        g2.fillRect((int)location.x + 35, (int)location.y - 5, width - 15, height);

        //nose
        g2.setColor(color_2);
        g2.fillOval((int)(location.x + width - 55), (int)(location.y + height + 15), (width/2), (width/2));
        g2.fillOval((int)(location.x + width - 7), (int)(location.y + height + 15), (width/2), (width/2));

        //pupils
        g2.fillOval((int)(location.x - 12), (int)(location.y + 8), width - 34, height - 13);
        g2.fillOval((int)(location.x + 42), (int)(location.y + 8), width - 34, height - 13);
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
                animStrategy = new DragonAnimFalling(this);
            }
        } else if (state == STATE_FALLING) {
            if (location.y >= Main.win.canvas.height) {
                state = STATE_DONE;
            }
        } else if (state == STATE_DONE) {
            super.done = true;
            notifyEvent(); // Dragon died!
            Font font = new Font("Courier New",Font.BOLD, 40);
            gameData.fixedObject.add(new Text("YOU WIN", 250, 200, Color.YELLOW, font));
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
