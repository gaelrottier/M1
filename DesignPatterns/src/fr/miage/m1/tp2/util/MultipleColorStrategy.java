package fr.miage.m1.tp2.util;

import java.awt.Color;
import java.util.Random;

public class MultipleColorStrategy implements IColorStrategy {

    @Override
    public Color getColor() {
        Random rand = new Random();
        return ColorUtility.getInstance(rand.nextInt(255), Color.black).getColorFromColorCube();
    }

}
