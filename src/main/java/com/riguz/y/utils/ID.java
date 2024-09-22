package com.riguz.y.utils;

import java.util.Objects;

public class ID {
    private final long client;
    private final long clock;

    public ID(long client, long clock) {
        if (client < 0 || clock < 0)
            throw new IllegalArgumentException("client id or clock id should not be negative");
        this.client = client;
        this.clock = clock;
    }

    public long getClient() {
        return client;
    }

    public long getClock() {
        return clock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id = (ID) o;
        return client == id.client && clock == id.clock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, clock);
    }
}
