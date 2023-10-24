package com.tester.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 数据库实用程序
 *
 * @author zz
 * @date 2023/09/24
 */
public class DatabaseUtils {

    public static SqlSession getSqlsession() throws IOException {
        Reader reader = Resources.getResourceAsReader("database-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = factory.openSession();
        return session;


    }
}
