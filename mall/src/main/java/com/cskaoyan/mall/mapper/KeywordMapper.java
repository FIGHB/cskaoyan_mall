package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.KeywordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface KeywordMapper {
    long countByExample(KeywordExample example);

    int deleteByExample(KeywordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    List<Keyword> selectByExample(KeywordExample example);

    Keyword selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByExample(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);

    List<Keyword> queryKeyWordList(@Param("keyword") String keyword,@Param("url") String url);

    long queryKeyWordListTotal(@Param("keyword") String keyword,@Param("url") String url);

    int insertKeyWordList(@Param("keyword") Keyword keyword);

    Keyword queryKeyWordById(@Param("id") int id);

    void updateKeyWord(@Param("keyword") Keyword keyword);

    void deleteKeyWordById(Integer id);
}