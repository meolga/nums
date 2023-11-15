package com.example.myapplication;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {
    private final int[][] field;
    private final int size;
    private final ModelChangeListener[][] listeners;


    public Model(int size) {
        this.size = size;
        listeners = new ModelChangeListener[size][size];
        field = new int[size][size];

        for (int i = 1; i < size; i++)
            for (int j = 1; j < size; j++) {
                field[i][j] = Math.floorMod((int) (Math.random() * 10), 3) + 1;
            }
    }

    public void addListener(int i, int j, ModelChangeListener listener) {
        this.listeners[i][j] = listener;
    }

    public int get(int i, int j) {
        return field[i][j];
    }

    public void left(int i) {
        int v0 = field[i][0];
        int j;
        for (j = 0; j < size - 1; j++) {
            field[i][j] = field[i][j + 1];
            listeners[i][j].valueChanged(i, j, field[i][j]);
        }

        field[i][size - 1] = v0;
        listeners[i][size - 1].valueChanged(i, size - 1, field[i][size - 1]);
    }

    public void right(int i) {
        int v0 = field[i][size - 1];
        int j;
        for (j = size - 1; j > 0; j--) {
            field[i][j] = field[i][j - 1];
            listeners[i][j].valueChanged(i, j, field[i][j]);
        }

        field[i][j] = v0;
        listeners[i][j].valueChanged(i, j, field[i][j]);
    }

    public void up(int j) {
        int v0 = field[0][j];
        int i;
        for (i = 0; i < size - 1; i++) {
            field[i][j] = field[i + 1][j];
            listeners[i][j].valueChanged(i, j, field[i][j]);
        }
        field[i][j] = v0;
        listeners[i][j].valueChanged(i, j, field[i][j]);
    }

    public void down(int j) {
        int v0 = field[size - 1][j];
        int i;
        for (i = size - 1; i > 0; i--) {
            field[i][j] = field[i - 1][j];
            listeners[i][j].valueChanged(i, j, field[i][j]);
        }
        field[i][j] = v0;
        listeners[i][j].valueChanged(i, j, field[i][j]);
    }

}
