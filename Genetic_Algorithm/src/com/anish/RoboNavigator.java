/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anish;

import java.util.ArrayList;

/**
 *
 * @author anish1992
 */
public class RoboNavigator {
    
    private static final int do_Nothing = 0;
    private static final int move_Forward = 1;
    private static final int turn_Clockwise = 2;
    private static final int turn_Counter_Clockwise = 3;
    
    private static enum Direction {BACKWARD, LEFT, FORWARD, RIGHT};
    
    private static final int maxMOves = 50;
    private int noOfMoves = 0;
    private RobotRoute route = new RobotRoute();
    private Position current = new Position(0,0);
    private Direction dir = Direction.FORWARD;
    private Integer detectorsAsInteger = -1;
    private ArrayList<Position> routePositions = new ArrayList<Position>();
    private ArrayList<String> routeString = new ArrayList<String>();
    public RoboNavigator()
    {
        routePositions.add(current);
    }

    public ArrayList<String> getRouteString()
    {
        return routeString;
    }
    
    public ArrayList<Position> activate(ArrayList<Integer> solutionActions){
        
        routeString.add("Position | Detectors (back|right|left|front) | Detectors As Integer |          Action\n");
        routeString.add("-------------------------------------------------------------------------------------------------------\n");
        
        boolean continueFlg = true;
        while(continueFlg){
            Integer solutionAction = solutionActions.get(getRouteCurrentPos());
            noOfMoves++;
            if ((route.getRoutePosValue(current) != RobotRoute.end) && (noOfMoves < maxMOves))
            {
                switch (solutionAction)
                {
                    case do_Nothing:
                        routeString.add("00 - do nothing\n");
                        continueFlg = false;
                        break;
                    case move_Forward:
                        routeString.add("01 - move forward\n");
                        stepFrwd(current);
                        break;
                    case turn_Clockwise:
                        chngeDir(turn_Clockwise);
                        routeString.add("10 - turn clockwise\n");
                        break;
                    case turn_Counter_Clockwise:
                        chngeDir(turn_Counter_Clockwise);
                        routeString.add("11 - turn counter-clockwise\n");
                        break;     
                }
                detectorsAsInteger = -1;
            } else continueFlg = false;
        }
        
        return routePositions;
    }
    
    private void stepFrwd(Position currentPos)
    {
        Position newPos = new Position(currentPos);
        switch(dir)
        {
            case BACKWARD:
                currentPos.setY(currentPos.getY()-1);
                break;
            case FORWARD:
                currentPos.setY(currentPos.getY()+1);
                break;
            case LEFT:
                currentPos.setX(currentPos.getX()+1);
                break;
            case RIGHT:
                currentPos.setX(currentPos.getX()-1);
                break;
        }
        if (route.getRoutePosValue(currentPos) == RobotRoute.wall)
        {
            currentPos.setX(newPos.getX());
            currentPos.setY(newPos.getY());
        }else routePositions.add(new Position(currentPos));
    }
    
    private void chngeDir(int turnDir)
    {
        switch(dir)
        {
            case BACKWARD:
                if (turnDir == turn_Clockwise) dir = Direction.LEFT;
                else if (turnDir == turn_Counter_Clockwise) dir = Direction.RIGHT;
                break;
            case FORWARD:
                if (turnDir == turn_Clockwise) dir = Direction.RIGHT;
                else if (turnDir == turn_Counter_Clockwise) dir = Direction.LEFT;
                break;
            case LEFT:
                if (turnDir == turn_Clockwise) dir = Direction.FORWARD;
                else if (turnDir == turn_Counter_Clockwise) dir = Direction.BACKWARD;
                break;
            case RIGHT:
                if (turnDir == turn_Clockwise) dir = Direction.BACKWARD;
                else if (turnDir == turn_Counter_Clockwise) dir = Direction.FORWARD;
                break;     
        }
    }
    
    private Integer getRouteCurrentPos()
    {
        Position frontPos = null, leftPos = null, rightPos = null, backPos = null;
        if (detectorsAsInteger == -1)
        {
            int x = current.getX();
            int y = current.getY();
            switch(dir)
            {
                case BACKWARD:
                    frontPos = new Position(x, y-1);
                    leftPos = new Position(x-1, y);
                    rightPos = new Position(x+1, y);
                    backPos = new Position(x, y+1);
                    break;
                case LEFT:
                    frontPos = new Position(x+1, y);
                    leftPos = new Position(x, y-1);
                    rightPos = new Position(x, y+1);
                    backPos = new Position(x-1, y);
                    break;
                case FORWARD:
                    frontPos = new Position(x, y+1);
                    leftPos = new Position(x+1, y);
                    rightPos = new Position(x-1, y);
                    backPos = new Position(x, y-1);
                    break;
                case RIGHT:
                    frontPos = new Position(x-1, y);
                    leftPos = new Position(x, y+1);
                    rightPos = new Position(x, y-1);
                    backPos = new Position(x+1, y);
                    break;    
            }
            calcDetectors(frontPos, leftPos, rightPos, backPos);
        }
        
        return detectorsAsInteger;
    }
    
    private void calcDetectors(Position front, Position left, Position right, Position back)
    {
        detectorsAsInteger = 0;
        String backDetector = "0" ,frontDetector="0",leftDetector="0",rightDetector="0";
        if(route.getRoutePosValue(front) == RobotRoute.wall)
        {
            detectorsAsInteger+=1;
            frontDetector="1";
        }
        if(route.getRoutePosValue(back) == RobotRoute.wall)
        {
            detectorsAsInteger+=8;
            backDetector="1";
        }
        if(route.getRoutePosValue(left) == RobotRoute.wall)
        {
            detectorsAsInteger+=2;
            leftDetector="1";
        }
        if(route.getRoutePosValue(right) == RobotRoute.wall)
        {
            detectorsAsInteger+=4;
            rightDetector="1";
        }
        String add0 ="";
        if(detectorsAsInteger<=9)
            add0="0";
        routeString.add("   "+this.current+"                " + backDetector +
        rightDetector + leftDetector + frontDetector + "                               " + add0 +
        detectorsAsInteger.toString() + "             ");

    }
    
}
