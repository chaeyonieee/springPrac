package com.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;



@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	
	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
		
	}
	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return memberDAO.insertMember(member);
	}
	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}
	@Override
	public int modMember(MemberVO member) throws DataAccessException {
		return memberDAO.updateMember(member);
	}
	@Override
	public MemberVO modMemberForm(String id) throws DataAccessException {
		return memberDAO.selectMemberById(id);
	}
	
}
/*<5>
setter�� �̿��ؼ� �ŰԺ������� ���������
memberdao���� memberdao������ �ְ�
this.memberDAO = memberDAO;�� ���ؼ� ������

��������� �� ���� ����

*/

/*
���⿡ memberDao��� �ʵ忡 memberdao���� �־��ش� 
@���� ������ @Autowired�� ������񽺶�� ��ü���� ->bean�±�
@�� �����ڰ� ����͸� �����ϴ�:����������
���̺귯���� sqlsession�� ���̺귯���� �̿��ϴ� ���ڵ��̾ �����ڰ� ����� ���� ����x

@service= 'service��ü�� �������ּ���'��� ���̴�
�׷��� new������ ����ִ°�? bean�±׵� ���� -> memberdao�ʵ忡�� autowired�� �������� �����ߴ�

dao�� �Ѿ��





 */