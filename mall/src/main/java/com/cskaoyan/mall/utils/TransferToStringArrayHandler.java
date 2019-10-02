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

/*国旭*/
@MappedTypes(String[].class)
public class TransferToStringArrayHandler implements TypeHandler<String[]> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, String[] strings, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonArray = null;
        try {
            jsonArray = objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index,jsonArray);
    }

    @Override
    public String[] getResult(ResultSet resultSet, String parameterName) throws SQLException {
        String value = resultSet.getString(parameterName);
        return parseStringToStringArray(value);
    }

    private String[] parseStringToStringArray(String value) {
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
    public String[] getResult(ResultSet resultSet, int index) throws SQLException {
        String string = resultSet.getString(index);
        return parseStringToStringArray(string);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return parseStringToStringArray(string);
    }
}
