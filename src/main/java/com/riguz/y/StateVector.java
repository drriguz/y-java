package com.riguz.y;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StateVector implements Iterable<Map.Entry<ClientID, Long>> {
    Map<ClientID, Long> states = new HashMap<>();

    public static StateVector from(BlockStore ss) {
        return new StateVector();
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }

    public int length() {
        return states.size();
    }

    public long get(ClientID client) {
        return states.getOrDefault(client, 0L);
    }

    public boolean contains(ID id) {
        return id.clock <= get(id.client);
    }

    public void increaseBy(ClientID client, long delta) {
        if (delta > 0) {
            states.merge(client, delta, Long::sum);
        }
    }

    public void setMin(ClientID client, long clock) {
        states.merge(client, clock, Math::min);
    }

    public void setMax(ClientID client, long clock) {
        states.merge(client, clock, Math::max);
    }

    public void merge(StateVector other) {
        for (var entry : other) {
            setMax(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Iterator<Map.Entry<ClientID, Long>> iterator() {
        return states.entrySet().iterator();
    }
}
