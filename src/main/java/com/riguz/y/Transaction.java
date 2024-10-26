package com.riguz.y;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    Store store;
    StateVector beforeState;
    StateVector afterState;
    List<ID> mergeBlocks;
    DeleteSet deleteSet;
    boolean committed;

    public Transaction(Store store) {
        mergeBlocks = new ArrayList<>();
        deleteSet = new DeleteSet();
        committed = false;
    }
}
