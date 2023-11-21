package com.example.myapplication.utils;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

public class Element {
    private final Position pos;
    private final int value;

    public Element(int value, Position position) {
        this.pos = position;
        this.value = value;
    }

    public Position getPos() {
        return pos;
    }

    public int getValue() {
        return value;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String toString() {
        return String.format("v=%d pos(%d;%d)", value, pos.getX(), pos.getY());
    }
}
