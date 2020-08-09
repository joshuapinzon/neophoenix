package model.machinegun;

import java.awt.*;
import java.util.Random;

public class MachineGunAnimExploding implements MachineGunAnimStrategy {

    Random rand = new Random();
    int n = rand.nextInt(10) + 1;

    MachineGun context;

    public MachineGunAnimExploding(MachineGun context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color= Color.ORANGE;
        ++context.size;
    }
}
