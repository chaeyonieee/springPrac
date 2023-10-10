package com.spring.ex01;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MemberDAO {
	public static SqlSessionFactory sqlMapper = null;
	
	
	
	
	private  static SqlSessionFactory getInstance() {
		if(sqlMapper == null) {
			try {
				String resource ="mybatis/SqlMapConfig.xml";//xml 읽고 데이터베이스와의 연동 준비하기
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);//마이바티스를 이용하는 sqlMapper 객채를 가져옵니다.
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}

	public List<MemberVO> selectAllMemberList(){
		sqlMapper = getInstance();//항상 사용해야 db연결되는것
		SqlSession session = sqlMapper.openSession();//실제 member.xml의 sql문을 호출하는데 사용되는 sqlsession 객체를 가져옵니다.
		List<MemberVO> memlist = null;//<memberVO형의>list list형으로 만들려고
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}
	
	
}
