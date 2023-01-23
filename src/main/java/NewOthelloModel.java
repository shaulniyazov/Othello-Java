package main.java;

import java.awt.*;
import java.util.ArrayList;

public class NewOthelloModel {
    MyPoint grid[][] = new MyPoint[8][8];
    boolean whoseTurn = false;
    MyPoint endPoint;

//todo make stuff public/private/whatever
    public NewOthelloModel(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new MyPoint(j, i, CellState.None);
            }
        }
    }

    public void setPointState(int i, int j, CellState state){
        grid[i][j].state = state;
    }

    public CellState whoseTurn(){
        return !whoseTurn ? CellState.Black  : CellState.White;
    }
    //CellState state  = whose turn it is now

    boolean makeMove(int row, int col, CellState state){
        //todo this should call isMoveLegal, then if that's true, call flip
        System.out.println(grid[row][col].state.toString2());
        boolean isLegal = false;
        MyPoint[] arr = getEightDirections(row, col).toArray(new MyPoint[0]);
        // this checks to see if its an unoccupied space
        if(grid[row][col].state.equals(CellState.None)){

            for (int i = 0; i < arr.length; i++) {
                if(arr[i] == null)
                    continue;
                if(arr[i].state.equals(oppositeState(state))
                        && isMoveFlippable(row, col, state,i)){
                    isLegal = true;
                    flipPieces(arr[i],endPoint,i);
                }
            }
        }

        return isLegal;
    }

    boolean isMoveLegal(int row, int col, CellState state){
        System.out.println(grid[row][col].state.toString2());
        boolean isLegal = false;
        //todo the array declaration should be after the state check. if its not None, why get directions?
        MyPoint[] arr = getEightDirections(row, col).toArray(new MyPoint[0]);
        // this checks to see if it's an unoccupied space
        if(grid[row][col].state.equals(CellState.None)){

            for (int i = 0; i < arr.length; i++) {
                if(arr[i] == null)
                    continue;
                if(arr[i].state.equals(oppositeState(state))
                        && isMoveFlippable(row, col, state,i)){
                    isLegal = true;
                   flipPieces(arr[i],endPoint,i);
                }
            }
        }

            return isLegal;
    }

    boolean isMoveFlippable(int row, int col, CellState state, int direction){
        MyPoint point;
        int start, end, counter  = 0;
        int startX = col,startY = row;
        switch (direction) {
            case 0:
                start = row;
                while (isLocationInBoundsAndUnoccupied(row, col)) {

                    point = grid[row][col];

                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        end = row;
                        counter += (start - end - 1);
                        System.out.println("counter " + counter);
                        return true;
                    }

                    else
                        row--;
                }
                break;
            case 1:
                start = row;
                while (isLocationInBoundsAndUnoccupied(row, col)) {
                    point = grid[row][col];
                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        end = row;
                        counter += (end - start - 1);
                        System.out.println("counter " + counter);
                        return true;
                    }
                    else
                        row++;
                }
                    break;
            case 2:
                start = col;
                while (isLocationInBoundsAndUnoccupied(row, col)) {
                    point = grid[row][col];
                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        end = col;
                        counter += (start - end - 1);
                        System.out.println("counter " + counter);
                        return true;
                    }
                    else
                        col--;
                }
                break;
            case 3:
                start = col;
                while (isLocationInBoundsAndUnoccupied(row, col)) {
                    point = grid[row][col];
                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        end = col;
                        counter += (end - start - 1);
                        System.out.println("counter " + counter);
                        return true;
                    }
                    else
                        col++;
                }
                break;
            case 4:

                while (isLocationInBoundsAndUnoccupied(row, col)) {
                    point = grid[row][col];
                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        int endX = col, endY = row;

                        counter += distance(startX,endX,startY,endY);
                        System.out.println("counter " + counter);
                        return true;
                    }
                    else {
                        row--;
                        col++;
                    }
                }
                break;
            case 5:

                while (isLocationInBoundsAndUnoccupied(row, col)) {
                    point = grid[row][col];
                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        int endX = col, endY = row;
                        counter += distance(startX,endX,startY,endY);
                        System.out.println("counter " + counter);
                        return true;
                    }
                    else {
                        row++;
                        col++;
                    }
                }
                break;
            case 6:
                while (isLocationInBoundsAndUnoccupied(row, col)) {
                    point = grid[row][col];
                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        int endX = col, endY = row;
                        counter += distance(startX,endX,startY,endY);
                        System.out.println("counter " + counter);
                        return true;
                    }
                    else {
                        row++;
                        col--;
                    }
                }
                break;
            case 7:
                while (isLocationInBoundsAndUnoccupied(row, col)) {
                    point = grid[row][col];
                    if (point.state.equals(state)){
                        endPoint= grid[row][col];
                        int endX = col, endY = row;
                        counter += distance(startX,endX,startY,endY);
                        System.out.println("counter " + counter);
                        return true;
                    }
                    else {
                        row--;
                        col--;
                    }
                }
                break;

        }

        return false;
    }

    boolean isLocationInBoundsAndUnoccupied(int row, int col){
        if(row < 0 || col < 0 || row > 7 || col > 7) return false;
        return true;
    }

    public CellState getCellState(int row, int col){
        return grid[row][col].state;
    }

    private CellState oppositeState(CellState state) {
        return state.equals(CellState.Black) ? CellState.White : CellState.Black;
    }

    private ArrayList<MyPoint> getEightDirections(int row, int col){
        Point point = new Point(col, row);
        System.out.println("point: " + point.toString());
        ArrayList<MyPoint> pointArr = new ArrayList<>();

        //north - 0
        if(isLocationInBoundsAndUnoccupied(point.y -1, point.x))
            pointArr.add(grid[point.y-1][point.x]);
        else
            pointArr.add(null);
        //south - 1
        if(isLocationInBoundsAndUnoccupied(point.y +1, point.x))
            pointArr.add(grid[point.y+1][point.x]);
        else
            pointArr.add(null);
        //west - 2
        if(isLocationInBoundsAndUnoccupied(point.y, point.x-1))
            pointArr.add(grid[point.y][point.x-1]);
        else
            pointArr.add(null);
        //east - 3
        if(isLocationInBoundsAndUnoccupied(point.y, point.x+1))
            pointArr.add(grid[point.y][point.x+1]);
        else
            pointArr.add(null);
        //northeast -4
        if(isLocationInBoundsAndUnoccupied(point.y -1, point.x+1))
            pointArr.add(grid[point.y -1][point.x +1]);
        else
            pointArr.add(null);
        //southeast - 5
        if(isLocationInBoundsAndUnoccupied(point.y +1, point.x +1))
            pointArr.add(grid[point.y +1][point.x +1]);
        else
            pointArr.add(null);
        //southwest - 6
        if(isLocationInBoundsAndUnoccupied(point.y +1, point.x -1))
            pointArr.add(grid[point.y +1][point.x -1]);
        else
            pointArr.add(null);
        //northwest - 7
        if(isLocationInBoundsAndUnoccupied(point.y -1, point.x-1))
            pointArr.add(grid[point.y -1][point.x -1]);
        else
            pointArr.add(null);

        return pointArr;
    }

    public void flipPieces(MyPoint startPoint, MyPoint endPoint, int direction){
        switch (direction){
            case 0:
                for (int i = startPoint.y; i > endPoint.y; i--) {
                    grid[i][startPoint.x].state = endPoint.state;
                }
                break;
            case 1:
                for (int i = startPoint.y; i < endPoint.y; i++) {
                    grid[i][startPoint.x].state = endPoint.state;
                }
                break;
            case 2:
                for (int i = startPoint.x; i > endPoint.x; i--) {
                    grid[startPoint.y][i].state = endPoint.state;
                }
                break;
            case 3:
                for (int i = startPoint.x; i < endPoint.x; i++) {
                    grid[startPoint.y][i].state = endPoint.state;
                }
                break;
            case 4:
                for (int i = startPoint.y,  j = startPoint.x; i > endPoint.y; i--) {
                    grid[i][j].state = endPoint.state;
                    j++;
                }
                break;
            case 5:
                for (int i = startPoint.y, j = startPoint.x ; i < endPoint.y; i++) {
                        grid[i][j].state = endPoint.state;
                        j++;

                }
                break;
            case 6:
                for (int i = startPoint.y, j = startPoint.x; i < endPoint.y; i++) {
                        grid[i][j].state = endPoint.state;
                        j--;
                }
                break;
            case 7:
                for (int i = startPoint.y, j = startPoint.x; i > endPoint.y; i--) {
                    grid[i][j].state = endPoint.state;
                    j--;
                }

        }
    }

    private int distance(int x1, int x2, int y1, int y2){
        return (int) Math.sqrt( ( Math.pow(x2- x1,2) + Math.pow(y2- y1,2) ) ) - 1;
    }

    enum CellState{
        None, Black, White;

        @Override
        public String toString() {
            if(this == CellState.Black)
                return "\u2B24";
            if(this == CellState.White)
                return "\u25CB";
            return null;
        }

        public String toString2() {
            if(this == CellState.Black)
                return "Black";
            if(this == CellState.White)
                return "White";
            return "None";
        }
    }

    private class MyPoint extends Point{
        int x,y;
        CellState state;

        MyPoint(int x, int y, CellState state){
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }
}


