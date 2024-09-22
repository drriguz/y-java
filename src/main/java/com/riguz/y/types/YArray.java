package com.riguz.y.types;

import com.riguz.y.utils.ArraySearchMarker;

import java.util.ArrayList;
import java.util.List;

public final class YArray extends AbstractType {
    private final List<Object> prelimContent;
    private final List<ArraySearchMarker> searchMarker;

    public YArray() {
        this.prelimContent = new ArrayList<>();
        this.searchMarker = new ArrayList<>();
    }

    public void insert(int index, List<Object> content) {
        if (this.doc != null) {

        } else {
            this.prelimContent.addAll(index, content);
        }
    }

    public List<Object> toArray() {
        return typeListToArray(this);
    }
}
