package model.vitalityWave;

import java.awt.*;
import java.util.Random;

public class VitalityWaveAnimExploding implements VitalityWaveAnimStrategy {

    Random rand = new Random();
    int n = rand.nextInt(10) + 1;

    VitalityWave context;

    public VitalityWaveAnimExploding(VitalityWave context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.color= Color.BLUE;
        context.size += context.size + 10;
    }
}
