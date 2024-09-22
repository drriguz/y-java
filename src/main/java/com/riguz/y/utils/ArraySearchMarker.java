package com.riguz.y.utils;

import com.riguz.y.structs.Item;

import java.util.concurrent.atomic.AtomicLong;

public class ArraySearchMarker {
    private Item p;
    private int index;
    private long timestamp;

    private static final AtomicLong globalSearchMarkerTimestamp = new AtomicLong(-1);

    public ArraySearchMarker(Item p, int index) {
        this.p = p;
        this.index = index;
        this.refreshTimestamp();
    }

    public void refreshTimestamp() {
        this.timestamp = globalSearchMarkerTimestamp.incrementAndGet();
    }

    public Item getP() {
        return p;
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void update(Item p, int index) {
        
    }
}
