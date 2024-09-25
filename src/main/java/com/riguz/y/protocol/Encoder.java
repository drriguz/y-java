package com.riguz.y.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * https://github.com/yjs/y-protocols/blob/master/PROTOCOL.md
 */
public class Encoder {
    protected final ByteArrayOutputStream encoder = new ByteArrayOutputStream();

    public void writeVarUnsignedInt(long num) {
        while (num > 127) {
            byte b = (byte) (128 | (127 & num));
            write(b);
            num >>= 7;
        }
        byte last = (byte) (127 & num);
        write(last);
    }

    public void writeVarByteArray(byte[] arr) throws IOException {
        writeVarUnsignedInt(arr.length);
        encoder.write(arr);
    }

    public byte[] utf8(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public void writeVarString(String str) throws IOException {
        writeVarByteArray(utf8(str));
    }

    public byte[] toBytes() {
        return encoder.toByteArray();
    }

    private void write(byte number) {
        encoder.write(number);
    }
}
