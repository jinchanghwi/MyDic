package com.mydic.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	@Override
	public List<BoardVo> getMyBoard(BoardVo vo) {
		
		return boardDao.getMyBoard(vo);
	}

	@Override
	public void boardInsert(BoardVo vo) {
		boardDao.boardInsert(vo);
		
	}

	@Override
	public BoardVo content(BoardVo vo) {
		
		return boardDao.content(vo);
	}

	@Override
	public void boardUpdate(BoardVo vo) {
		boardDao.boardUpdate(vo);
		
	}

	@Override
	public void boardDelete(BoardVo vo) {
		boardDao.boardDelete(vo);
		
	}

	@Override
	public int totalRecord(BoardVo vo) {
		
		return boardDao.totalRecord(vo);
	}

}
