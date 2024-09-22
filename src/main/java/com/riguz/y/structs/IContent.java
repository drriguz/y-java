package com.riguz.y.structs;

import java.util.List;

public interface IContent {
    long getLength();

    List<Object> getContent();

    boolean isCountable();

    IContent copy();

    IContent splice(long offset);

    boolean mergeWith(IContent right);
}
