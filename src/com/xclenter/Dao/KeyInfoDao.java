package com.xclenter.Dao;

import com.xclenter.Module.KeyRecord;
import com.xclenter.Util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KeyInfoDao {

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

            KeyRecord keyRecord = new KeyRecord(id,key,modifier,source);
            return keyRecord;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.elegantlyClose(connection,statement,resultSet);
        }
    }
}
