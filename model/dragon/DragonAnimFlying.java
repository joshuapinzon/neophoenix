package model.dragon;

import controller.Main;

public class DragonAnimFlying implements DragonAnimStrategy {

    Dragon context;

    public DragonAnimFlying(Dragon context) {
        this.context = context;
    }

    @Override
    public void animate() {

        if (context.location.x >= 550) {
            context.movingRight = false;
        } else if (context.location.x <= 50) {
            context.movingRight = true;
        }
        if (context.movingRight) context.location.x += context.UNIT_MOVE;
        else context.location.x -= context.UNIT_MOVE;

    }
}
