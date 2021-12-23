package com.mydic.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	ChatRepository chatRepo;
	@Override
	public void insertChat(ChatVo vo) {
		chatRepo.save(vo);
	}

	@Override
	public List<ChatVo> getChat(ChatVo vo) {
		return (List<ChatVo>)chatRepo.findAllByOrderByIdx();
	}

	@Override
	public void deleteChat(ChatVo vo) {
		chatRepo.deleteAll();
	}

}
