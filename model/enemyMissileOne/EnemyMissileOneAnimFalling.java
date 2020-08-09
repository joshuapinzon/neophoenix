package model.enemyMissileOne;

import java.awt.*;

public class EnemyMissileOneAnimFalling implements EnemyMissileOneAnimStrategy {

    EnemyMissileOne context;

    public EnemyMissileOneAnimFalling(EnemyMissileOne context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color = Color.RED;
        context.color_2 = Color.RED;
        context.location.y += context.UNIT_MOVE_FALLING;
        context.width -= context.width - 10;
        context.height -= context.height - 10;
    }
}
