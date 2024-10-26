package com.riguz.y;

public class Doc {
    private final ClientID clientId;
    private final Store store;

    private Doc(ClientID clientId, Store store) {
        this.clientId = clientId;
        this.store = store;
    }

    public Doc() {
        this(new Options());
    }

    public Doc(ClientID clientId) {
        this(clientId, new Store());
    }

    public Doc(Options options) {
        this(options.clientId, new Store());
    }

    public Transaction transact() {
        return new Transaction();
    }
}
