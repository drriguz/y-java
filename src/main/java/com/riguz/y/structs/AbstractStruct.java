package com.riguz.y.structs;

import com.riguz.y.utils.ID;

public abstract class AbstractStruct {
    private ID id;
    private long length;

    public AbstractStruct(ID id, long length) {
        this.id = id;
        this.length = length;
    }

//    public abstract boolean isDeleted();
//
//    public abstract boolean mergeWith(AbstractType right);
//
//    public abstract void write();
//
//    public abstract void integrate();
}
