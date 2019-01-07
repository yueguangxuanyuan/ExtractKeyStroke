package com.xclenter.Module;

public class SummaryBerifRecord {
    int id;
    String action;

    public SummaryBerifRecord(int id, String action) {
        this.id = id;
        this.action = action;
    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }
}
