package com.mydic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public void join(UserVo vo) {
		userDao.join(vo);
	}

	@Override
	public String login(UserVo vo) {
		return userDao.login(vo);
	}

	@Override
	public String overlap(UserVo vo) {
		return userDao.overlap(vo);
	}

	@Override
	public UserVo myinfo(UserVo vo) {
		return userDao.myinfo(vo);
	}

	@Override
	public void myinfoupdate(UserVo vo) {
		userDao.myinfoupdate(vo);
		
	}

	@Override
	public void myinfodelete(UserVo vo) {
		userDao.myBoarddelete(vo);
		userDao.myComudelete(vo);
		userDao.myinfodelete(vo);
		
	}

	@Override
	public void myinfoupdatepwd(UserVo vo) {
		userDao.myinfoupdatepwd(vo);
		
	}

	@Override
	public String getFilename(UserVo vo) {

		return userDao.getFilename(vo);
	}

	@Override
	public void myBoarddelete(UserVo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void myComudelete(UserVo vo) {
		// TODO Auto-generated method stub
		
	}

}
