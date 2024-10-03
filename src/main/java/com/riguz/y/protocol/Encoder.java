package com.riguz.y.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
 * https://github.com/yjs/y-protocols/blob/master/PROTOCOL.md
 *
 * 123456=0b111|1000100|1000000
 * ->(1)1000000|(1)1000100|(0)____111
 * -> 0xc0     | 0xc4     | 0x07
 */
public class Encoder {
    /*
        see: https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Number/MAX_SAFE_INTEGER
        ie. 2^53 -1
     */
    public static final long MAX_SAFE_INTEGER = 9007199254740991L;

    protected final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    public void writeVarUint(long num) {
        if (num > MAX_SAFE_INTEGER)
            throw new IllegalArgumentException("Number too large than max safe integer(2^53-1)");
        while (num > 127) {
            byte b = (byte) (128 | (127 & num));
            write(b);
            num >>= 7;
        }
        byte last = (byte) (127 & num);
        write(last);
    }

    public void writeVarByteArray(byte[] arr) throws IOException {
        writeVarUint(arr.length);
        buffer.write(arr);
    }

    public byte[] utf8(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public void writeVarString(String str) throws IOException {
        writeVarByteArray(utf8(str));
    }

    public byte[] toBytes() {
        return buffer.toByteArray();
    }

    private void write(byte number) {
        buffer.write(number);
    }
}
