package com.xclenter.Common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionEnumClass {
    public static final String contentInsert = "contentInsert";
    public static final String contentDelete = "contentDelete";
    public static final String contentReplace = "contentReplace";

    public static final String keyDown = "keyDown";
    public static final String keyUp = "keyUp";
    public static final String keyCmd = "keyCmd";

    public static List<String> getStaticAttriValueList(){
        List<String> attriList = new ArrayList<>();

        Class theClass = ActionEnumClass.class;
        Field[] fields = theClass.getFields();

        for(Field field : fields){
            if(Modifier.isStatic(field.getModifiers())){
                try {
                    attriList.add(String.valueOf(field.get(theClass)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return attriList;
    }

}
