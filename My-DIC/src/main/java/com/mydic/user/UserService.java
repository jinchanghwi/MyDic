package com.mydic.user;

public interface UserService {
	public void join(UserVo vo);
	public String login(UserVo vo);
	public String overlap(UserVo vo);
	public UserVo myinfo(UserVo vo);
	public void myinfoupdate(UserVo vo);
	public void myinfoupdatepwd(UserVo vo);
	public void myinfodelete(UserVo vo);
	public void myBoarddelete(UserVo vo);
	public void myComudelete(UserVo vo);
	public String getFilename(UserVo vo);
}
