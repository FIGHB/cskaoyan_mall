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

@MappedTypes(Integer[].class)
public class TransferToIntegerArrayHandler implements TypeHandler<Integer[]> {

    //将传入的 Integer 数组转换成 json 格式的数据 然后存入数据库
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Integer[] integers, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonArray = null;
        try {
            jsonArray = objectMapper.writeValueAsString(integers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index,jsonArray);
    }

    //将数据库中的 json 格式的数据解析成 Integer 数组取出
    @Override
    public Integer[] getResult(ResultSet resultSet, String parame) throws SQLException {
        String value = resultSet.getString(parame);
        return parseStringToIntegerArray(value);
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, int i) throws SQLException {
        String value = resultSet.getString(i);
        return parseStringToIntegerArray(value);
    }

    @Override
    public Integer[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String value = callableStatement.getString(i);
        return parseStringToIntegerArray(value);
    }

    private Integer[] parseStringToIntegerArray(String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        Integer[] strings = new Integer[0];
        if(value==null){
            return strings;
        }
        try {
            strings = objectMapper.readValue(value, Integer[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
