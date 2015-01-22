package fr.miage.m1.tp2.util;

import java.awt.Color;

public class UniqueColorStrategy implements IColorStrategy{

    private Color color;
    
    public UniqueColorStrategy(Color color) {
        this.color = color;
    }
    
    @Override
    public Color getColor() {
        return ColorUtility.getInstance(0, this.color).getUniqueColor();
    }

}
