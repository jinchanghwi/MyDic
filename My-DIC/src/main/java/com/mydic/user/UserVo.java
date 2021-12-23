package com.mydic.user;

import org.springframework.web.multipart.MultipartFile;


public class UserVo {
	private int idx;;
	private String id;
	private String pwd;
	private String pwd_hash;
	private String name;
	private String email;
	private String filename;
	private MultipartFile uploadfile;
	
	
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPwd_hash() {
		return pwd_hash;
	}
	public void setPwd_hash(String pwd_hash) {
		this.pwd_hash = pwd_hash;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserVo [idx=" + idx + ", id=" + id + ", pwd=" + pwd + ", pwd_hash=" + pwd_hash + ", name=" + name
				+ ", email=" + email + ", filename=" + filename + ", uploadfile=" + uploadfile + "]";
	}


	
	
}
