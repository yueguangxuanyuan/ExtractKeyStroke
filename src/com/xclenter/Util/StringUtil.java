package com.xclenter.Util;

import java.util.List;

public class StringUtil {

    public static String convertToSqlList(List<String> strList){
        StringBuilder sb = new StringBuilder();

        sb.append("(");

        if(strList != null){
            for(String str : strList){
                sb.append("'");
                sb.append(str);
                sb.append("',");
            }
        }

        sb.replace(sb.length()-1,sb.length(),")");
        return sb.toString();
    }

}
