package model.missile;

public class MissileAnimShooting implements MissileAnimStrategy {

    Missile context;

    public MissileAnimShooting(Missile context) {
        this.context = context;
    }

    @Override
    public void animate() {
        double radians = Math.atan2(context.target.y - context.location.y, context.target.x - context.location.x);
        context.location.x += context.UNIT_MOVE * Math.cos(radians);
        context.location.y += context.UNIT_MOVE * Math.sin(radians);
    }
}
