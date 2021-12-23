package com.mydic.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
	public List<BoardVo> getMyBoard(BoardVo vo);
	public void boardInsert(BoardVo vo);
	public void boardUpdate(BoardVo vo);
	public void boardDelete(BoardVo vo);
	public BoardVo content(BoardVo vo);
	public int totalRecord(BoardVo vo);
}
