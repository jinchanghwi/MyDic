package com.mydic.community;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydic.user.UserServiceImpl;
import com.mydic.user.UserVo;

@Controller
public class CommunityController {
	@Autowired
	CommunityServiceImpl communityService;
	@Autowired
	UserServiceImpl userService;

	@RequestMapping("/community.do")
	public String community(CommunityVo vo,Model model) {
		vo.setPageSize(10);
		vo.setListSize(10);
			int totalRecord=communityService.totalRecord(vo);
			System.out.println(totalRecord);
		vo.setTotalRecord(totalRecord);
		if(totalRecord%10==0) {
			vo.setTotalPage(totalRecord/vo.getListSize());
		}
		else {
			vo.setTotalPage(totalRecord/vo.getListSize()+1);
		}
		if(vo.getNowRecord()==0) {
			vo.setNowRecord(1);
		}
		if(vo.getNowRecord()==0) {
			vo.setNowPage(1);
		}
		else {
			vo.setNowPage(vo.getNowRecord()/vo.getListSize()+1);
		}
		vo.setStartPage((vo.getNowPage()-1)/vo.getPageSize()*vo.getPageSize()+1);
		vo.setEndPage(vo.getStartPage()+vo.getPageSize()-1);
		model.addAttribute("page", vo);
		model.addAttribute("li", communityService.getCommunity(vo));
		return "community";
	}
	@RequestMapping("/newcommunity.do")
	public String newcommunity(CommunityVo vo,Model model1,Model model2) {
		return "newcommunity";
	}
	
	@RequestMapping("/comucontent.do")
	public String comucontent(CommunityVo vo,Model model,HttpSession session) {
		model.addAttribute("mainCon", communityService.comucontent(vo)); // 메인컨텐츠
		UserVo userVo = new UserVo();
		userVo.setId(vo.getId());
		model.addAttribute("writerImg", userService.getFilename(userVo)); // 작성자의 사진
		userVo.setId((String)session.getAttribute("id"));
		model.addAttribute("loginImg", userService.getFilename(userVo)); // 로그인중인 사람의 사진
		vo.setParent(vo.getIdx());
		model.addAttribute("replyInfo", communityService.getReply(vo)); // 댓글정보
		return "comucontent";
	}
	@RequestMapping("/insertCommunity.do")
	public String insertCommunity(CommunityVo vo,HttpSession session) {
		vo.setId((String)session.getAttribute("id"));
		vo.setParent(0);
		communityService.insertCommunity(vo);
		return "redirect:community.do";
	}
	@RequestMapping("/deleteCommunity.do")
	public String deleteCommunity(CommunityVo vo,HttpSession session) {
		communityService.deleteCommunity(vo);
		return "redirect:community.do";
	}
	
	@RequestMapping("/insertReply.do")
	public String insertReply(CommunityVo vo,HttpSession session) {
		UserVo userVo = new UserVo();
		userVo.setId(vo.getId()); // 본문으로 돌아갈 아이디
		vo.setId((String)session.getAttribute("id")); // 작성자 아이디
		vo.setTitle("Reply");
		System.out.println(vo.getParent());
		if(vo.getParent()==0) { // 본문에 댓글달기
			vo.setSort(communityService.getSort(vo));
			vo.setLvl(1);
			vo.setParent(vo.getIdx());
		}
		else {
			userVo.setId(vo.getStaticId());
			vo.setLvl(2);
			vo.setParent(vo.getIdx());
		}
		System.out.println(vo.getParent());
		System.out.println(vo.getSort());
		communityService.insertReply(vo);
		return "redirect:comucontent.do?idx="+ vo.getIdx() +"&id=" + userVo.getId();
	}
	@RequestMapping("/deleteReply.do")
	public String deleteReply(CommunityVo vo,HttpSession session) {
		communityService.deleteCommunity(vo);
		return "redirect:comucontent.do?idx="+ vo.getParent() +"&id=" + vo.getId();
	}
	
	
}
