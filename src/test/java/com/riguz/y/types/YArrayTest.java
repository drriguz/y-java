package com.riguz.y.types;

import com.riguz.y.utils.YDoc;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class YArrayTest {
    @Test
    void testBasicUpdate() {
        var doc1 = new YDoc();
        var doc2 = new YDoc();
        List<Object> content = List.of("hi");
        doc1.getArray("array").insert(0, content);
        var update = doc1.encodeStateAsUpdate();
        doc2.applyUpdate(update);

        assertThat(content).hasSameElementsAs(doc2.getArray("array").toArray());
    }
}