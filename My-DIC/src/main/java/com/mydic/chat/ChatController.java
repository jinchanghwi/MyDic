package com.mydic.chat;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class ChatController {
	@Autowired
	ChatService chatService;
	
	@RequestMapping("/chat.do")
	public String chat(ChatVo vo,Model model) {
		return "chat/chat";
	}
	
	@RequestMapping("/insertChat.do")
	public @ResponseBody void insertChat(ChatVo vo) {
		chatService.insertChat(vo);
	}
	@RequestMapping("/deleteChat.do")
	public @ResponseBody void deleteChat() {
		chatService.deleteChat(null);
	}
	
	@RequestMapping(value="/getChat.do")
	public @ResponseBody void getChat(ChatVo vo,HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(chatService.getChat(vo));
		response.setContentType("text/json;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}
}
