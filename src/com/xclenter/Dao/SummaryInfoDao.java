package com.xclenter.Dao;

import com.xclenter.Common.ActionEnumClass;
import com.xclenter.Module.SummaryBerifRecord;
import com.xclenter.Util.DBUtil;
import com.xclenter.Util.StringUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class SummaryInfoDao {
    public static int loadSummaryBerifRecords(LinkedList<SummaryBerifRecord> summaryBerifRecords, int startIndex , int patchSize, String dbFilePath){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DBUtil.getDBConnection(dbFilePath);
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder("select id,action from summary_info where action in ");
            sql.append(StringUtil.convertToSqlList(ActionEnumClass.getStaticAttriValueList()) );
            sql.append(" limit ");
            sql.append(startIndex);
            sql.append(",");
            sql.append(patchSize);

            resultSet = statement.executeQuery(sql.toString());
            if(!resultSet.isBeforeFirst()){
                return -1;
            }

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String action = resultSet.getString("action");

                SummaryBerifRecord summaryBerifRecord = new SummaryBerifRecord(id,action);

                summaryBerifRecords.add(summaryBerifRecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.elegantlyClose(connection,statement,resultSet);
        }

        return startIndex+patchSize;
    }
}
