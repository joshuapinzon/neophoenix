package model.enemyMissileOne;

import controller.Main;

public class EnemyMissileOneAnimFlying implements EnemyMissileOneAnimStrategy {

    EnemyMissileOne context;

    public EnemyMissileOneAnimFlying(EnemyMissileOne context) {
        this.context = context;
    }

    @Override
    public void animate() {
        if (context.location.x >= Main.win.canvas.width) {
            context.movingRight = false;

        } else if (context.location.x <= 35) {
            context.movingRight = true;
        }
        if(context.movingRight) context.location.x += 5;
        else context.location.x -= 5;

        if (context.location.y >= Main.win.canvas.height) {
            context.movingDown = false;

        } else if (context.location.y <= 35) {
            context.movingDown = true;
        }
        if(context.movingDown) context.location.y += 5;
        else context.location.y -= 5;
    }
}
