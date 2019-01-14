package com.xclenter.Business;

import com.xclenter.Common.ActionEnumClass;
import com.xclenter.Common.CommonConstant;
import com.xclenter.Dao.ContentInfoDao;
import com.xclenter.Dao.KeyInfoDao;
import com.xclenter.Dao.SummaryInfoDao;
import com.xclenter.Module.*;
import com.xclenter.Util.IOUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KeyExtractor {

    private List<KeyAction> generateKeyActionList(String sourceRootFilePath){
        List<KeyAction> keyActionList = new ArrayList<>();
        String dbFilePath = sourceRootFilePath + File.separator+"Dao" +File.separator + "log.db";

        LinkedList<KeyRecord> keyRecordLinkedList = new LinkedList<>();
        boolean isNotReadToEnd = true;
        int index = 0;
        final int patchSize = 100;
        KeyRecord preKeyRecord = null;

        while(isNotReadToEnd){
            index = KeyInfoDao.loadKeyRecords(keyRecordLinkedList,index,patchSize,dbFilePath);
            if(index < 0){
                isNotReadToEnd = false;
            }

            while(keyRecordLinkedList.size() > 0){
                KeyRecord firstRecord = keyRecordLinkedList.pollFirst();
                int id = firstRecord.getId();
                long timeticks = firstRecord.getTime();
                String key = CommonConstant.UnknownKey;
                boolean isInputChar = false;
                switch (firstRecord.getSource()){
                   case ActionEnumClass.keyDown:
                       if(firstRecord.getKey().equals(CommonConstant.ImeProcessedKey)){
                           key = CommonConstant.ImeProcessedKey;
                       }else if (keyRecordLinkedList.size() > 0){
                           KeyRecord nextRecord = keyRecordLinkedList.peek();
                           if(nextRecord.getSource().equals(ActionEnumClass.keyInput)){
                               isInputChar = true;
                                key = nextRecord.getKey();
                                keyRecordLinkedList.removeFirst();
                           }else{
                               key = firstRecord.translateKey();
                           }
                       }else if(!isNotReadToEnd){
                           key = firstRecord.translateKey();
                       }
                       break;
                   case ActionEnumClass.keyCmd:
                       key = firstRecord.getKey();
                       break;
                }
                if(key.equals(CommonConstant.UnknownKey)){
                    break;
                }

                KeyAction keyAction = new KeyAction(id,key,isInputChar,timeticks);
                keyActionList.add(keyAction);
            }
        }

        return keyActionList;
    }


    private List<KeyLatency> generateKeyLatency(String sourceRootFilePath ,List<KeyAction> keyActionListOuter ){
        List<KeyLatency> keyLatencyList = new ArrayList<>();

        List<KeyAction> keyActionList = keyActionListOuter == null ? generateKeyActionList(sourceRootFilePath) : keyActionListOuter;

        long preTime = -1;

        for(KeyAction keyAction : keyActionList){
            int latency = -1;
            if(preTime > 0){
                latency = (int)(keyAction.getTimeticks() - preTime);
            }
            preTime = keyAction.getTimeticks();
            String key = keyAction.getKey();
            KeyLatency keyLatency = new KeyLatency(key,latency);
            keyLatencyList.add(keyLatency);
        }

        return keyLatencyList;
    }


    public void extractKeyLatency(String sourceRootFilePath,String targetFilePath){
        List<KeyAction> keyActionList = generateKeyActionList(sourceRootFilePath);
        List<KeyLatency> keyLatencyList = generateKeyLatency(sourceRootFilePath,keyActionList);

        IOUtil.writeListToFile(KeyAction.class,keyActionList,targetFilePath);
        IOUtil.writeListToFile(KeyLatency.class,keyLatencyList,targetFilePath);
    }


}
