package com.example.myapplication.utils;

import java.util.ArrayList;
import java.util.List;


public class Chains {
    private final List<Chain> chains;

    public Chains() {
        this.chains = new ArrayList<>();
    }

    public void add(Chain chain) {
        this.chains.add(chain);
    }

    public List<Chain> getChainList() {
        return this.chains;
    }

    public int size() {
        return chains.size();
    }
}
