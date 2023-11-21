package com.example.myapplication.utils;

import java.util.ArrayList;
import java.util.List;

public class ChainsContainer {
    private final List<Chains> group;

    public ChainsContainer() {
        this.group = new ArrayList<>();
    }

    public void add(Chains chains) {
        group.add(chains);
    }

    public void clear() {
        group.clear();
    }

    public boolean isEmpty() {
        return group.isEmpty();
    }

    public List<Chains> getChainsList() {
        return group;
    }
}
