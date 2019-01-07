package com.xclenter.Module;

public class KeyLatency {
    String key;
    int keyLatency;

    public KeyLatency(String key, int keyLatency) {
        this.key = key;
        this.keyLatency = keyLatency;
    }

    public String getKey() {
        return '\'' + key+"\'";
    }

    public int getKeyLatency() {
        return keyLatency;
    }
}
