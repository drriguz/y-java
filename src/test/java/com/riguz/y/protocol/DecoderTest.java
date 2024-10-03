package com.riguz.y.protocol;

import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {
    private long readUint(byte[] buf) {
        Decoder decoder = new Decoder(buf);
        return decoder.readVarUint();
    }

    @Test
    void testReadUint() {
        assertEquals(123456, readUint(fromHexString("c0c407")));
        assertEquals(9007199254740991L, readUint(fromHexString("ffffffffffffff0f"))); // 2^53 – 1
    }

    @Test
    void testReadContinuously() {
        var bytes = fromHexString("c0c407ffffffffffffff0f");
        Decoder decoder = new Decoder(bytes);
        assertEquals(123456, decoder.readVarUint());
        assertEquals(9007199254740991L, decoder.readVarUint());
    }

    @Test
    void testReadUnexpectedEnding() {
        var bytes = fromHexString("ff");
        Decoder decoder = new Decoder(bytes);
        assertThrows(Exception.class, decoder::readVarUint);
    }

    @Test
    void testReadBytes() {
        var bytes = fromHexString("03151f03");
        Decoder decoder = new Decoder(bytes);
        var r = decoder.readBytes();
        assertEquals(3, r.length);
    }

    @Test
    void testReadIllegalBytes() {
        var bytes = fromHexString("0315");
        Decoder decoder = new Decoder(bytes);
        assertThrows(Exception.class, decoder::readBytes);
    }

    @Test
    void testReadString() {
        var bytes = fromHexString("10e4bda0e5a5bdefbc8ce4b896e7958c21");
        Decoder decoder = new Decoder(bytes);
        var r = decoder.readString();
        assertEquals("你好，世界!", r);
    }

    @Test
    void testhasContent() {
        var bytes = fromHexString("c0c407ffffffffffffff0f");
        Decoder decoder = new Decoder(bytes);
        assertTrue(decoder.hasContent());
        assertEquals(123456, decoder.readVarUint());
        assertTrue(decoder.hasContent());
        assertEquals(9007199254740991L, decoder.readVarUint());
        assertFalse(decoder.hasContent());
    }

    private byte[] fromHexString(String str) {
        HexFormat hexFormat = HexFormat.of();
        return hexFormat.parseHex(str);
    }
}