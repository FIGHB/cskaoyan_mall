package com.cskaoyan.mall.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String[].class)
public class TransferStringArrayHandler implements TypeHandler<String[]> {

    //由javabean转换成数据库接收的类型
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonArray = objectMapper.writeValueAsString(strings);
            preparedStatement.setString(i,jsonArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    //以下三个为由数据库查询的结果转换成javabean类型
    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        String value = resultSet.getString(s);
        return parseString2StringArray(value);
    }

    private String[] parseString2StringArray(String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[0];
        if(value==null){
            return strings;
        }
        try {
            strings = objectMapper.readValue(value, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return parseString2StringArray(value);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return parseString2StringArray(value);
    }
}
