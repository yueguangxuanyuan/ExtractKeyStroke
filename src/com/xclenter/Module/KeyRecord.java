package com.xclenter.Module;

public class KeyRecord {
    int id;
    String key;
    String modifier;
    String source;
    long time;

    public KeyRecord(int id, String key, String modifier, String source,long time) {
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

    public long getTime() {
        return time;
    }

    private static String[] ModifierArray = {"Alt","Ctrl","Shift","Win","Caps"};
    public String translateKey(){
        char[] modifierMarkArray = modifier.toCharArray();
        StringBuilder sb = new StringBuilder(key);
        for(int i = 0 ; i < ModifierArray.length ; i++){
            if(modifierMarkArray[i] == '1'){
                sb.append("+");
                sb.append(ModifierArray[i]);
            }
        }
        return sb.toString();
    }
}
