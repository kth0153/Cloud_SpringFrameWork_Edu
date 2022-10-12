package org.mvc.member;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
	MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> memberList = memberDAO.selectAllMember();
		
		
		return memberList;
	}
	
	public void addMember(MemberVO m) {
		memberDAO.InsertMember(m);
	}
	
	public MemberVO findMember(String _id) {
		MemberVO memInfo = null;
		
		memInfo = memberDAO.selectMember(_id);
		
		return memInfo;
	}
	
	public void modMember(MemberVO memberVO) {
		memberDAO.UpdateMember(memberVO);
	}
	
	public void delMember(String id) {
		memberDAO.DeleteMember(id);
	}
	
	
}
