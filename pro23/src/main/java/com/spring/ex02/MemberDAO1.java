package com.spring.ex02;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;


public class MemberDAO1 {
	public static SqlSessionFactory sqlMapper = null;
	private  static SqlSessionFactory getInstance() {
		if(sqlMapper == null) {
			try {
				String resource ="mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}

	/*public List<MemberVO> selectAllMemberList(){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memlist = null;
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;*/
	
	
	
	public String selectName() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		String name = session.selectOne("mapper.member.selectName");
		return name;
	}
	public String selectPwd() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		String pwd = session.selectOne("mapper.member.selectPwd");
		return pwd;
	}

	public List<MemberVO> selectAllMemberList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
