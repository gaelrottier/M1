package fr.miage.m1.tp2.util;

import java.awt.Color;

public class ColorUtility {

	private static ColorUtility instance = null;
	
	private final float colorPhase;
	private float r = 0.0f;
	private float g = 0.0f;
	private float b = 0.0f;
	private Color uniqueColor;
	
	private ColorUtility(int distinctColors, Color uniqueColor) {
		float creaturesCountCubeRoot = (float) Math.pow(distinctColors, 1.0 / 3.0);
		colorPhase = (float) (1.0 / creaturesCountCubeRoot);
		this.uniqueColor = uniqueColor;
	}
	
	public static ColorUtility getInstance(int distinctColors, Color uniqueColor) {
		if (instance == null) {
			instance = new ColorUtility(distinctColors,uniqueColor);
		}
		return instance;
	}
	
	public Color getColorFromColorCube() {
		r += colorPhase;
		if (r > 1.0) {
			r -= 1.0f;
			g += colorPhase;
			if (g > 1.0) {
				g -= 1.0f;
				b += colorPhase;
				if (b > 1.0)
					b -= 1.0f;
			}
		}

		return new Color(r, g, b);
	}
	
	public Color getUniqueColor() {
		return this.uniqueColor;
	}
}
