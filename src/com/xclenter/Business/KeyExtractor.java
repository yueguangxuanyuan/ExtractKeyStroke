package com.xclenter.Business;

import com.xclenter.Common.ActionEnumClass;
import com.xclenter.Common.CommonConstant;
import com.xclenter.Dao.ContentInfoDao;
import com.xclenter.Dao.KeyInfoDao;
import com.xclenter.Dao.SummaryInfoDao;
import com.xclenter.Module.ContentRecord;
import com.xclenter.Module.KeyLatency;
import com.xclenter.Module.KeyRecord;
import com.xclenter.Module.SummaryBerifRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KeyExtractor {

    private boolean checkIfInputMatchKey(KeyRecord keyRecord,ContentRecord contentRecord){
        return false;
    }

    public List<KeyLatency> generateKeyLatency(String sourceRootFilePath){
        List<KeyLatency> keyLatencyList = new ArrayList<>();
        String dbFilePath = sourceRootFilePath + File.separator+"Dao" +File.separator + "log.db";

        LinkedList<SummaryBerifRecord> summaryBerifRecords = new LinkedList<>();
        boolean isNotEnd = true;
        int index = 0;
        final int patchSize = 100;
        KeyRecord preKeyRecord = null;

        while(isNotEnd){
            index = SummaryInfoDao.loadSummaryBerifRecords(summaryBerifRecords,index,patchSize,dbFilePath);
            if(index < 0){
                isNotEnd = false;
                continue;
            }

            while(summaryBerifRecords.size() > 0){
                SummaryBerifRecord firstRecord = summaryBerifRecords.getFirst();
                if(firstRecord.getAction().endsWith(ActionEnumClass.keyDown)){
                    KeyRecord keyDownRecord = KeyInfoDao.getKeyRecord(firstRecord.getId(),dbFilePath);
                    //找到下一个对应的内容插入事件
                    int contentRecordId = -1;
                    for(SummaryBerifRecord checkRecord : summaryBerifRecords){
                        if(checkRecord.getAction().startsWith(CommonConstant.ContentActionPrefix)){
                            contentRecordId = checkRecord.getId();
                            break;
                        }
                    }
                    if(contentRecordId <= 0){
                        //没有找到，说明记录还没有被读进来
                        break;
                    }

                    ContentRecord contentRecord = ContentInfoDao.getContentRecord(contentRecordId,dbFilePath);

                    boolean isMatch = checkIfInputMatchKey(keyDownRecord,contentRecord);

                    if(isMatch){
                        if(preKeyRecord != null){

                        }

                        preKeyRecord = keyDownRecord;
                    }

                }
                summaryBerifRecords.removeFirst();
            }
        }

        return keyLatencyList;
    }


    public void extractKeyLatency(String sourceRootFilePath,String targetFilePath,String targetOriginDataFilePaths){

    }


}
