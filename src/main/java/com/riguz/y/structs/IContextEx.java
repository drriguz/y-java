package com.riguz.y.structs;

public interface IContextEx extends IContent {
    int getRef();

    void integrate();

    void delete();

    void gc();

    void write();
}
