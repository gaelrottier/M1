package fr.miage.m1.tp2.util;

public class CercleTrigo {

	public static double NORD = Math.PI / 2;
	public static double SUD = 3 * Math.PI / 2;
	public static double EST = 0;
	public static double OUEST = Math.PI;
	
	public static double NORD_EST = Math.PI / 4;
	public static double NORD_OUEST = 3 * Math.PI / 4;
	public static double SUD_OUEST = 5 * Math.PI / 4;
	public static double SUD_EST = 7 * Math.PI / 4;
	
	public static double modulo2PI(double angle) {
		double newAngle = angle % (Math.PI*2);
		if (newAngle < 0) {
			newAngle = newAngle + Math.PI * 2;
		}
		return newAngle;
	}
}
