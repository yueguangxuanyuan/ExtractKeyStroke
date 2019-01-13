package com.xclenter.Module;

public class KeyRecord {
    int id;
    String key;
    String modifier;
    String source;
    String time;

    public KeyRecord(int id, String key, String modifier, String source,String time) {
        this.id = id;
        this.key = key;
        this.modifier = modifier;
        this.source = source;
        this.time = time;
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

    public String getTime() {
        return time;
    }
}
