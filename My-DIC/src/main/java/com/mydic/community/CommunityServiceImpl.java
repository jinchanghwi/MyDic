package com.mydic.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService{
	@Autowired
	CommunityDao communityDao;

	@Override
	public List<CommunityVo> getCommunity(CommunityVo vo) {
		
		return communityDao.getCommunity(vo);
	}

	@Override
	public CommunityVo comucontent(CommunityVo vo) {

		return communityDao.comucontent(vo);
	}

	@Override
	public void insertCommunity(CommunityVo vo) {
		communityDao.insertCommunity(vo);
		
	}

	@Override
	public void updateCommunity(CommunityVo vo) {
		communityDao.updateCommunity(vo);
		
	}

	@Override
	public void deleteCommunity(CommunityVo vo) {
		communityDao.deleteCommunity(vo);
		
	}

	@Override
	public void insertReply(CommunityVo vo) {
		communityDao.insertReply(vo);
		
	}

	@Override
	public void deleteReply(CommunityVo vo) {
		communityDao.deleteReply(vo);
		
	}

	@Override
	public List<CommunityVo> getReply(CommunityVo vo) {
		
		return communityDao.getReply(vo);
	}

	@Override
	public int getSort(CommunityVo vo) {
		
		return communityDao.getSort(vo);
	}

	@Override
	public int totalRecord(CommunityVo vo) {

		return communityDao.totalRecord(vo);
	}
}
