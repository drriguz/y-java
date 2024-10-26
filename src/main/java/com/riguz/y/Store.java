package com.riguz.y;

import com.riguz.y.types.Branch;

import java.util.Map;

public class Store {
    private Options options;
    private Map<String, Branch> types;
    private BlockStore blocks;
    private PendingUpdate pending;
    private DeleteSet pendingDs;
}
