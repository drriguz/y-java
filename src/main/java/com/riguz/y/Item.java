package com.riguz.y;

import com.riguz.y.types.TypeRef;

public final class Item extends Block {
    private ID id;
    private long length;
    private Block left;
    private Block right;
    private ID origin;
    private ID rightOrigin;
    private ItemContent content;
    private TypeRef parent;
    private String parentSub;
    private Block moved;
    private ItemFlags info;
}
