package com.ebupt.vnbo.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil {
	/**
	 * mybaits获取数据库链接
	 * @return
	 * @throws IOException
	 */
 public static SqlSession getSqlSession() throws IOException{
		
		Reader reader=Resources.getResourceAsReader("config/xml/Configuration.xml");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
		SqlSession session=sessionFactory.openSession();
		return session;
	}

}
