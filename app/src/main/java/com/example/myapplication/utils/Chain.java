package com.example.myapplication.utils;

import java.util.ArrayList;
import java.util.List;

public class Chain {
    private final List<Element> chain;

    public Chain() {
        chain = new ArrayList<>();
    }

    public void remove(int id) {
        if (id >= 0 && id < chain.size()) {
            chain.remove(id);
        }
    }

    public void add(Chain subChain) {
        chain.addAll(subChain.chain);
    }

    public void add(Element element) {
        chain.add(element);
    }

    public List<Element> getElentList() {
        return chain;
    }

    public int size() {
        return chain.size();
    }
}
