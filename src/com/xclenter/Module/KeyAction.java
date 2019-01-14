package com.xclenter.Module;

public class KeyAction {
    private int id;
    private String key;
    private boolean isInputChar;
    private long timeticks;

    public KeyAction(int id ,String key, boolean isInputChar, long timeticks) {
        this.id = id;
        this.key = key;
        this.isInputChar = isInputChar;
        this.timeticks = timeticks;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public boolean isInputChar() {
        return isInputChar;
    }

    public long getTimeticks() {
        return timeticks;
    }
}
