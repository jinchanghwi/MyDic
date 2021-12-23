package com.mydic.chat;

import java.util.List;

public interface ChatService {
	public void insertChat(ChatVo vo);
	public void deleteChat(ChatVo vo);
	public List<ChatVo> getChat(ChatVo vo);
}
