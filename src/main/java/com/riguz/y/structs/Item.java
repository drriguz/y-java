package com.riguz.y.structs;

import com.riguz.y.types.AbstractType;
import com.riguz.y.utils.ID;

public abstract class Item extends AbstractStruct {
    private Item left;
    private ID origin;
    private Item right;
    private ID rightOrigin;
    private AbstractType parent;
    private String parentSub;
    private IContent content;
    private int info;

    public Item(ID id, long length, Item left, ID origin, Item right, ID rightOrigin, AbstractType parent, String parentSub, IContent content) {
        super(id, length);
        this.left = left;
        this.origin = origin;
        this.right = right;
        this.rightOrigin = rightOrigin;
        this.parent = parent;
        this.parentSub = parentSub;
        this.content = content;

        this.info = this.content.isCountable() ? Info.COUNTABLE.mask : 0;
    }

    public boolean isCountable() {
        return hasMark(Info.COUNTABLE);
    }

    public boolean isDeleted() {
        return hasMark(Info.DELETED);
    }

    public IContent getContent() {
        return this.content;
    }

    public Item getRight() {
        return this.right;
    }

    protected boolean hasMark(Info info) {
        return (this.info & info.mask) > 0;
    }

    private enum Info {
        KEEP(1 << 0),
        COUNTABLE(1 << 1),
        DELETED(1 << 2),
        MARKER(1 << 3);

        final int mask;

        Info(int mask) {
            this.mask = mask;
        }
    }
}
