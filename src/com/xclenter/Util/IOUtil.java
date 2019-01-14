package com.xclenter.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class IOUtil {
    private static String checkNextUsableFileName(String fileName){
        int index = 0;

        boolean isNotEnd = true;
        String possibleFileName;
        do{
            possibleFileName = fileName;
            if(index > 0){
                possibleFileName += "-"+index;
            }
            File file = new File(possibleFileName);
            if(!file.exists()){
                isNotEnd = false;

            }
            index++;
        }while(isNotEnd);
        return possibleFileName;
    }

    public static void writeListToFile(Class targetClass,List objects,String targetFolderPath){
        String fileName = checkNextUsableFileName(targetFolderPath + File.separator + targetClass.getName());

        File file = new File(fileName);
        file.getParentFile().mkdirs();
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Field[] fields = targetClass.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            //写头部
            StringBuilder headlineContent = new StringBuilder();
            for(Field field :fields){
                headlineContent.append(field.getName());
                headlineContent.append(",");
            }
            headlineContent.replace(headlineContent.length()-1,headlineContent.length(),"\r\n");
            fileWriter.write(headlineContent.toString());

            //写内容
            for(Object o : objects){
                StringBuilder lineContent = new StringBuilder();
                for(Field field : fields){
                    lineContent.append(field.get(o));
                    lineContent.append(",");
                }
                lineContent.replace(lineContent.length()-1,lineContent.length(),"\r\n");
                fileWriter.write(lineContent.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            if(fileWriter != null){
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
