package com.spring.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

public interface MemberDAO {

	public List selectAllMemberList() throws DataAccessException;
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	public int updateMember(MemberVO memberVO) throws DataAccessException;
	public MemberVO selectMemberById(String id) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
	

	
}

/*
 * controller는 ioexception
 * dao는 db랑 연동해서 DataAccessException을 걸어주어야한다
 * 
 * */
 