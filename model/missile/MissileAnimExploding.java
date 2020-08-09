package model.missile;

import java.awt.*;
import java.util.Random;

public class MissileAnimExploding implements MissileAnimStrategy {

    Random rand = new Random();
    int n = rand.nextInt(10) + 1;

    Missile context;

    public MissileAnimExploding(Missile context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color= Color.RED;
        ++context.size;
    }
}
