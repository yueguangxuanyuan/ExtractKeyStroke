package com.xclenter.Module;

public class ContentRecord {
    private int id;
    private String operation;
    private String textFrom;
    private String textTo;

    public ContentRecord(int id, String operation, String textFrom, String textTo) {
        this.id = id;
        this.operation = operation;
        //去除单引号
        this.textFrom = textFrom.substring(1,textFrom.length()-1);
        this.textTo = textTo.substring(1,textFrom.length()-1);
    }

    public int getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public String getTextFrom() {
        return textFrom;
    }

    public String getTextTo() {
        return textTo;
    }
}
