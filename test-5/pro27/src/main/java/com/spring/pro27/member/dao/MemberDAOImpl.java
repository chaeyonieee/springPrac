package com.spring.pro27.member.dao;



import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.pro27.member.vo.MemberVO;


//@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
//������ �۾��� �ϰ��ִ� sqlSession�� �����ϸ�Ǵ°��̴�.	@Autowired�� ����
	//

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;//list ���ʸ� <memberVO>���̴� 

		membersList = sqlSession.selectList("mapper.member.selectAllMemberList"); //�������� ����Ʈ�� �ްڴ�, sqlsession�ּҴ� mappers�� ���� �ּ��̴� ,
		return membersList; //list������ �������ش�
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);//�������� :1 0?
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {

		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.update("mapper.member.updateMember", memberVO);//�������� :1 0?
		return result;
	}

	@Override
	public MemberVO selectMemberById(String id) throws DataAccessException {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.selectMemberById", id);//;�Ѱ��� ����, int, string ,memberVO�� �ϳ�	
		return memberVO;
	}

	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

/*
repository�� �����ϴ�(����Ҷ�� ���̴�.)-db�� ����Ǿ
dao�� access�ϴ� �����̾ throws DataAccessException�� �ʿ��ϴ�





*/