package com.example.myapplication;

public class Model {
    private final int[][] field;

    public Model(int size) {
        field = new int[size][size];

        for (int i = 1; i < size; i++)
            for (int j = 1; j < size; j++) {
                field[i][j] = Math.floorMod((int) (Math.random() * 10), 3) + 1;
            }
    }

    public int get(int i, int j) {
        return field[i][j];
    }

    public boolean move(int i, int j) {
        return
                left(i, j) ||
                right(i, j) ||
                down(i, j) ||
                up(i, j);
    }

    private boolean left(int i, int j) {
        if (i > 0 && field[i - 1][j] == 0) {
            field[i - 1][j] = field[i][j];
            field[i][j] = 0;
            return true;
        }
        return false;
    }

    private boolean right(int i, int j) {
        if (i < field.length && field[i + 1][j] == 0) {
            field[i + 1][j] = field[i][j];
            field[i][j] = 0;
            return true;
        }
        return false;
    }

    private boolean up(int i, int j) {
        if (j > 0 && field[i][j - 1] == 0) {
            field[i][j - 1] = field[i][j];
            field[i][j] = 0;
            return true;
        }
        return false;
    }

    private boolean down(int i, int j) {
        if (j < field[i].length && field[i][j + 1] == 0) {
            field[i][j + 1] = field[i][j];
            field[i][j] = 0;
            return true;
        }
        return false;
    }

}
