package model.dragon;

import java.awt.*;

public class DragonAnimFalling implements DragonAnimStrategy {

    Dragon context;

    public DragonAnimFalling(Dragon context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.BLACK;
        context.color_2 = Color.RED;
        context.location.y += context.UNIT_MOVE_FALLING;
    }
}
