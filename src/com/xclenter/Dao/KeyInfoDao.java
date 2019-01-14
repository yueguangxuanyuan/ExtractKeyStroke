package com.xclenter.Dao;

import com.xclenter.Common.ActionEnumClass;
import com.xclenter.Module.KeyRecord;
import com.xclenter.Util.DBUtil;
import com.xclenter.Util.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class KeyInfoDao {

    public static int loadKeyRecords(LinkedList<KeyRecord> keyRecords, int startIndex , int patchSize, String dbFilePath){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DBUtil.getDBConnection(dbFilePath);
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder("select * from key_info where source in ");
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
                String key = resultSet.getString("key");
                String modifier = resultSet.getString("modifier");
                String source = resultSet.getString("source");
                long timeticks = resultSet.getLong("timeticks");

                KeyRecord keyRecord = new KeyRecord(id,key,modifier,source,timeticks);

                keyRecords.add(keyRecord);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.elegantlyClose(connection,statement,resultSet);
        }

        return startIndex+patchSize;
    }

    public static KeyRecord getKeyRecord(int id ,String dbFilePath){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DBUtil.getDBConnection(dbFilePath);
            statement = connection.createStatement();

            String sql = "select id,key,modifier,source from key_info where id="+id;

            resultSet = statement.executeQuery(sql);
            resultSet.next();

            String key = resultSet.getString("key");
            String modifier = resultSet.getString("modifier");
            String source = resultSet.getString("source");
            long timeticks = resultSet.getLong("timeticks");

            KeyRecord keyRecord = new KeyRecord(id,key,modifier,source,timeticks);
            return keyRecord;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.elegantlyClose(connection,statement,resultSet);
        }
    }
}
