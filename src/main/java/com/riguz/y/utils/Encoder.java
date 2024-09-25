package com.riguz.y.utils;

public interface Encoder {
    byte[] toBytes();

    void resetDsCurVal();

    void writeDsClock(long clock);

    void writeDsLen(long len);
}
