package model.tracker;

import java.awt.*;

public class TrackerAnimFalling implements TrackerAnimStrategy {

    Tracker context;

    public TrackerAnimFalling(Tracker context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.RED;
        context.color_2 = Color.BLACK;
        context.location.y += context.UNIT_MOVE_FALLING;
        --context.width;
        --context.height;
    }
}
