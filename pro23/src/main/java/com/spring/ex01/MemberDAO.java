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
				String resource ="mybatis/SqlMapConfig.xml";//xml �а� �����ͺ��̽����� ���� �غ��ϱ�
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);//���̹�Ƽ���� �̿��ϴ� sqlMapper ��ä�� �����ɴϴ�.
				reader.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}

	public List<MemberVO> selectAllMemberList(){
		sqlMapper = getInstance();//�׻� ����ؾ� db����Ǵ°�
		SqlSession session = sqlMapper.openSession();//���� member.xml�� sql���� ȣ���ϴµ� ���Ǵ� sqlsession ��ü�� �����ɴϴ�.
		List<MemberVO> memlist = null;//<memberVO����>list list������ �������
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}
	
	
}
