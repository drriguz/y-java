package com.riguz.y.protocol;

import java.nio.charset.StandardCharsets;

import static com.riguz.y.protocol.Encoder.MAX_SAFE_INTEGER;

public class Decoder {
    private final byte[] buffer;
    private int offset;

    public Decoder(byte[] buffer) {
        this.buffer = buffer;
        offset = 0;
    }

    public long readVarUint() {
        long num = 0;
        int len = 0;
        while (offset < buffer.length) {
            long b = Byte.toUnsignedInt(this.buffer[this.offset++]);
            num = num | ((b & 127) << len);
            len += 7;
            if (b < 128)
                return num;
            if (num > MAX_SAFE_INTEGER)
                throw new RuntimeException("VarUint out of safe range(2^53-1)");
        }
        throw new RuntimeException("Unexpected end of varInt array");
    }

    public byte[] readBytes() {
        int length = (int) readVarUint();
        if (length < 0)
            throw new RuntimeException("Illegal negative byte length");
        if (length > (buffer.length - offset))
            throw new RuntimeException("Bytes length out of buffer range");
        byte[] arr = new byte[length];
        System.arraycopy(buffer, offset, arr, 0, length);
        offset += length;
        return arr;
    }

    public String readString() {
        var bytes = readBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public boolean hasContent() {
        return offset < buffer.length;
    }
}
