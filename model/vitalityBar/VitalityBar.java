package model.vitalityBar;

import model.GameFigure;

import java.awt.*;

public class VitalityBar extends GameFigure {

    public final int SIZE = 10;
    int width, height;

    public  VitalityBar(int x, int y) {

        super(x, y);
        width = SIZE;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setStroke(new BasicStroke(1)); //thickness of the line
        g2.setColor(Color.CYAN);
        g2.fillOval(400, 400, width, height);

    }

    @Override
    public void update() {
       // updateState();
        //animStrategy.animate(); //Strategy design pattern
    }


    @Override
    public int getCollisionRadius() {
        return 0;
    }
}