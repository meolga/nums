package com.example.myapplication.utils;

import java.util.Arrays;

public class MaskField {
    final int size;
    final int[][] field;

    private static final int OCCUPIED = 0xFF;
    private static final int BUSY = 0x1;
    private static final int FREE = 0;

    public MaskField(int size) {
        field = new int[size][size];
        this.size = size;
        this.clearAll();
    }

    public void clearAll() {
        for (int[] row : field) {
            Arrays.fill(row, FREE);
        }
    }

    public void clearBusy() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == BUSY) {
                    field[i][j] = FREE;
                }
            }
        }
    }

    public boolean isOccupied(Position pos) {
        return field[pos.getY()][pos.getX()] != FREE;
    }

    public boolean isNotOccupied(int x, int y) {
        return field[y][x] == FREE;
    }

    public void setTempMark(Position pos) {
        field[pos.getY()][pos.getX()] = BUSY;
    }

    public void setOccupied(Position pos) {
        field[pos.getY()][pos.getX()] = OCCUPIED;
    }


}
