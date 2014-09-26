/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.tp2;

import java.awt.Polygon;

/**
 *
 * @author Gaël
 */
public class Test {

    private int int1 = 42;
    private Integer integer = 2132321321;
    private String[] str = new String[]{"test1", "test2", "test3",};
    private Polygon p;

    public Test(int[] xpoints, int[] ypoints, int npoints) {
        p = new Polygon(xpoints, ypoints, npoints);
    }

}
