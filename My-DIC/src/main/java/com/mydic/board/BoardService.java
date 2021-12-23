package com.mydic.board;

import java.util.List;

public interface BoardService {
	public List<BoardVo> getMyBoard(BoardVo vo);
	public void boardInsert(BoardVo vo);
	public void boardUpdate(BoardVo vo);
	public void boardDelete(BoardVo vo);
	public BoardVo content(BoardVo vo);
	public int totalRecord(BoardVo vo);
}
