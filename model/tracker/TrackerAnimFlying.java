package model.tracker;

import controller.Main;

public class TrackerAnimFlying implements TrackerAnimStrategy {

    Tracker context;

    public TrackerAnimFlying(Tracker context) {
        this.context = context;
    }

    @Override
    public void animate() {

        var shooter = Main.gameData.fixedObject.get(Main.INDEX_SHOOTER);

        //if (context.movingRight) context.location.x += context.UNIT_MOVE;
        //else context.location.x -= context.UNIT_MOVE;

        if (context.location.x < shooter.location.x) {
            context.location.x += 1;
        } else if (context.location.x > shooter.location.x) {
            context.location.x -= 1;
        }

        if (context.location.y < shooter.location.y) {
            context.location.y += 1;
        } else if (context.location.y > shooter.location.y) {
            context.location.y -= 1;
        }

    }
}
