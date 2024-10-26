package com.riguz.y;

public class Options {
    final ClientID clientId;
    final OffsetKind offsetKind;
    final boolean skipGc;

    private Options(ClientID clientId, OffsetKind offsetKind, boolean skipGc) {
        this.clientId = clientId;
        this.offsetKind = offsetKind;
        this.skipGc = skipGc;
    }

    public Options() {
        this(new ClientID());
    }

    public Options(ClientID clientId) {
        this(clientId, OffsetKind.Bytes, false);
    }
}
