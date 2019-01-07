package com.xclenter.Business;

import com.xclenter.Common.ActionEnumClass;
import com.xclenter.Dao.SummaryInfoDao;
import com.xclenter.Module.KeyLatency;
import com.xclenter.Module.SummaryBerifRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KeyExtractor {
    public List<KeyLatency> generateKeyLatency(String sourceRootFilePath){
        List<KeyLatency> keyLatencyList = new ArrayList<>();
        String dbFilePath = sourceRootFilePath + File.separator+"Dao" +File.separator + "log.db";

        LinkedList<SummaryBerifRecord> summaryBerifRecords = new LinkedList<>();
        boolean isNotEnd = true;
        int index = 0;
        final int patchSize = 100;

        while(isNotEnd){
            index = SummaryInfoDao.loadSummaryBerifRecords(summaryBerifRecords,index,patchSize,dbFilePath);
            if(index < 0){
                isNotEnd = false;
                continue;
            }

            while(summaryBerifRecords.size() > 0){
                SummaryBerifRecord firstRecord = summaryBerifRecords.getFirst();
                if(firstRecord.getAction().endsWith(ActionEnumClass.keyDown)){


                }
                summaryBerifRecords.removeFirst();
            }
        }

        return keyLatencyList;
    }


    public void extractKeyLatency(String sourceRootFilePath,String targetFilePath,String targetOriginDataFilePaths){

    }


}
