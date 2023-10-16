package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;


@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
//실질적 작업을 하고있는 sqlSession만 설정하면되는것이다.	@Autowired로 연결
	//

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;

		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);//성공여부 :1 0?
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {

		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public int updateMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.update("mapper.member.updateMember", memberVO);//성공여부 :1 0?
		return result;
	}

	@Override
	public MemberVO selectMemberById(String id) throws DataAccessException {
		MemberVO memberVO = sqlSession.selectOne("mapper.member.selectMemberById", id);//;한가지 정보
		return memberVO;
	}
	
	
	
	
}

/*
repository라서 가능하다(저장소라는 뜻이다.)-db랑 연결되어서
dao에 access하는 역할이어서 throws DataAccessException이 필요하다





*/