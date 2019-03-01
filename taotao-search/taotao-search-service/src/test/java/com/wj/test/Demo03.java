package com.wj.test;


import com.wj.taotao.common.SearchItem;
import com.wj.taotao.search.mapper.SearchItemMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Demo03 {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() throws Exception {
        // 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        // 创建核心配置文件的输入流
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = ssfb.build(inputStream);
        // 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    static {
        try {
            // 创建SqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
            // 创建核心配置文件的输入流
            InputStream inputStream;
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            sqlSessionFactory = ssfb.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建SqlSessionFactory对象

    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) throws Exception{
        try {
            SearchItemMapper mapper = getSqlSession().getMapper(SearchItemMapper.class);
            List<SearchItem> searchItemList = mapper.getSearchItemList();
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
