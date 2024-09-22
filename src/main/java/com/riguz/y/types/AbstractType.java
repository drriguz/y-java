package com.riguz.y.types;

import com.riguz.y.structs.Item;
import com.riguz.y.utils.YDoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public sealed abstract class AbstractType
        permits YText, YArray, YMap {
    protected Item item;
    protected Map<String, Item> map;
    protected Item start;
    protected YDoc doc;
    protected long length;

    public void integrate(YDoc doc, Item item) {
        this.doc = doc;
        this.item = item;
    }

    public static List<Object> typeListToArray(AbstractType type) {
        var cs = new ArrayList<>();
        var n = type.start;
        while (n != null) {
            if (n.isCountable() && !n.isDeleted()) {
                var c = n.getContent().getContent();
                cs.addAll(c);
            }
            n = n.getRight();
        }
        return cs;
    }
}
