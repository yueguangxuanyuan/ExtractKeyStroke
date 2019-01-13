package com.xclenter.Dao;

import com.xclenter.Module.ContentRecord;
import com.xclenter.Util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContentInfoDao {

    public static ContentRecord getContentRecord(int id , String dbFilePath){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DBUtil.getDBConnection(dbFilePath);
            statement = connection.createStatement();

            String sql = "select operation,textfrom,textto from content_info where id="+id;

            resultSet = statement.executeQuery(sql);
            resultSet.next();

            String operation = resultSet.getString("operation");
            String textfrom = resultSet.getString("textfrom");
            String textto = resultSet.getString("textto");

            ContentRecord contentRecord = new ContentRecord(id,operation,textfrom,textto);
            return contentRecord;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.elegantlyClose(connection,statement,resultSet);
        }
    }
}
