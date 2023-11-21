package com.example.myapplication.utils;

public class Element {
    private Position pos;
    private int value;

    public Element(int value, Position position) {
        this.pos = position;
        this.value = value;
    }

    public void setPos(Position position) {
        pos = position;
    }

    public Position getPos() {
        return pos;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("v=%d pos(%d;%d)", value, pos.getX(), pos.getY());
    }
}
