package com.mydic.chat;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<ChatVo, Integer>{
	List<ChatVo> findAllByOrderByIdx();
}
