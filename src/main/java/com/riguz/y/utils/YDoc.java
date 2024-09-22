package com.riguz.y.utils;

import com.riguz.y.types.AbstractType;
import com.riguz.y.types.YArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class YDoc {
    private final Map<String, AbstractType> share;
    private final List<Transaction> transactionCleanups;
    private Transaction transaction;

    public YDoc() {
        this.share = new HashMap<>();
        this.transactionCleanups = new ArrayList<>();
    }

    public YArray getArray(String name) {
        return getOrCreate(name, YArray::new);
    }

    public void transact(Consumer<Transaction> fun, Object origin, boolean local) {
        boolean initialCall = false;
        if (transaction == null) {
            initialCall = true;
            transaction = new Transaction();
            transactionCleanups.add(transaction);
            if (transactionCleanups.size() == 1) {

            }
        }

        try {
            fun.accept(transaction);
        } finally {
            if (initialCall && transactionCleanups.get(0) == transaction) {

            }
        }
    }

    public byte[] encodeStateAsUpdate() {
        return null;
    }

    public void applyUpdate(byte[] update) {

    }

    private <T extends AbstractType> T getOrCreate(String name, Supplier<T> creator) {
        if (!share.containsKey(name)) {
            T type = creator.get();
            type.integrate(this, null);
            share.put(name, type);

            return type;
        }
        return (T) share.get(name);
    }
}
