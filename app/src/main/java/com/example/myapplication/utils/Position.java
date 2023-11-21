package com.example.myapplication.utils;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
