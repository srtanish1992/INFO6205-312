/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anish;

/**
 *
 * @author anish1992
 */
public class RobotRoute {
    
    public static final int wall = 0;
    public static final int begin = 1;
    public static final int route = 2;
    public static final int end = 3;
    public static Integer coordinates[][] = new Integer[][] {
        {begin, route, wall, wall, wall, wall},
        {wall, route, wall, wall, wall, wall},
        {wall, route, route, wall, wall, wall},
        {wall, wall, route, wall, wall, wall},
        {wall, wall, route, wall, wall, wall},
        {end, route, route, wall, wall, wall}
    };
    
    public Integer getRoutePosValue(Position pos){
        int returnValue = wall;
        if ((pos.getX() >= 0 && pos.getX() < RobotRoute.coordinates.length) &&
                (pos.getY() >= 0 && pos.getY() < RobotRoute.coordinates[0].length))
                returnValue = RobotRoute.coordinates[pos.getY()][pos.getX()];
        return returnValue;
    } 
    
}
