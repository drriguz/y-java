package com.riguz.y;

import java.util.Objects;
import java.util.Random;

public class ClientID {
    public static final long MAX_U32 = 0xFFFFFFFFL;

    private final long id;

    public ClientID(long id) {
        this.id = id;
    }

    public ClientID() {
        this(new Random().nextLong(0, MAX_U32));
    }

    @Override
    public String toString() {
        return "ClientId{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientID clientId = (ClientID) o;
        return id == clientId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
