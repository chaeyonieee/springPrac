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
setter을 이용해서 매게변수값을 전달해줬다
memberdao형의 memberdao변수가 있고
this.memberDAO = memberDAO;를 통해서 값전달

여기까지가 전 파일 설명

*/

/*
여기에 memberDao라는 필드에 memberdao값을 넣어준다 
@으로 끝났다 @Autowired로 멤버서비스라는 객체생성 ->bean태그
@은 개발자가 만든것만 가능하다:의존성주입
라이브러리나 sqlsession은 라이브러리로 이용하는 블랙코드이어서 개발자가 만들수 없다 권한x

@service= 'service객체를 생성해주세요'라는 뜻이다
그러면 new예약어는 어디에있는가? bean태그도 없다 -> memberdao필드에서 autowired로 의존성을 주입했다

dao로 넘어가기





 */