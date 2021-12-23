package com.mydic.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	@Autowired
	BoardServiceImpl boardService;
	
	
	@RequestMapping("board.do")
	public String board(BoardVo vo,Model model,HttpSession session) {
		vo.setId((String)session.getAttribute("id"));

		vo.setPageSize(10);
		vo.setListSize(10);
			int totalRecord=boardService.totalRecord(vo);
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
		if(boardService.getMyBoard(vo).size()>0) {
			model.addAttribute("li", boardService.getMyBoard(vo));
		}
		else {
			vo.setCh1("");
			System.out.println(boardService.totalRecord(vo));
			if(boardService.totalRecord(vo)!=0) {
				vo.setResult("noresult");
			}
			model.addAttribute("li", null);			
		}
		model.addAttribute("page", vo);
		return "board";
	}
	
	@RequestMapping("newnote.do")
	public String newnote() {
		return "newnote";
	}
	
	@RequestMapping("/noteok.do")
	public String boardInsert(BoardVo vo) {
		boardService.boardInsert(vo);
		return "redirect:board.do";
	}
	
	@RequestMapping("/noteupdate.do")
	public String boardUpdate(BoardVo vo) {
		boardService.boardUpdate(vo);
		return "redirect:board.do";
	}
	@RequestMapping("/notedelete.do")
	public String boardDelete(BoardVo vo) {
		boardService.boardDelete(vo);
		return "redirect:board.do";
	}
	@RequestMapping("/content.do")
	public String content(BoardVo vo,Model model) {
		model.addAttribute("m", boardService.content(vo));
		return "content";
	}
	
}
