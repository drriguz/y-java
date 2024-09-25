package com.riguz.y.protocol;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HexFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EncoderTest {
    private byte[] encodeUint(long num) {
        Encoder encoder = new Encoder();
        encoder.writeVarUnsignedInt(num);
        return encoder.toBytes();
    }

    private byte[] encodeBytes(byte[] arr) {
        Encoder encoder = new Encoder();
        try {
            encoder.writeVarByteArray(arr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return encoder.toBytes();
    }

    private byte[] encodeString(String str) {
        Encoder encoder = new Encoder();
        try {
            encoder.writeVarString(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return encoder.toBytes();
    }

    @Test
    void writeVarUnsignedInt() {
        assertEquals("c0c407", toHexString(encodeUint(123456)));
        assertEquals("ffffffffffffff0f", toHexString(encodeUint(9007199254740991L))); // 2^53 – 1
    }

    @Test
    void writeVarUnsignedIntArray() {
        assertEquals("00", toHexString(encodeBytes(new byte[]{})));
        assertEquals("03151f03", toHexString(encodeBytes(new byte[]{0x15, 0x1f, 0x03})));
    }

    @Test
    void writeVarString() {
        assertEquals("0568656c6c6f", toHexString(encodeString("hello")));
        assertEquals("10e4bda0e5a5bdefbc8ce4b896e7958c21", toHexString(encodeString("你好，世界!")));
    }

    private String toHexString(byte[] bytes) {
        HexFormat hexFormat = HexFormat.of();
        return hexFormat.formatHex(bytes);
    }
}