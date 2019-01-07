package com.xclenter.Module;

public class KeyRecord {
    int id;
    String key;
    String modifier;
    String source;

    public KeyRecord(int id, String key, String modifier, String source) {
        this.id = id;
        this.key = key;
        this.modifier = modifier;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getModifier() {
        return modifier;
    }

    public String getSource() {
        return source;
    }
}
