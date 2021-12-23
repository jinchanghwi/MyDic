package com.mydic.community;

import java.util.List;

public interface CommunityService {
	public void insertCommunity(CommunityVo vo);
	public void updateCommunity(CommunityVo vo);
	public void deleteCommunity(CommunityVo vo);
	public void insertReply(CommunityVo vo);
	public void deleteReply(CommunityVo vo);
	public List<CommunityVo> getCommunity(CommunityVo vo);
	public List<CommunityVo> getReply(CommunityVo vo);
	public CommunityVo comucontent(CommunityVo vo);
	public int getSort(CommunityVo vo);
	public int totalRecord(CommunityVo vo);
}
