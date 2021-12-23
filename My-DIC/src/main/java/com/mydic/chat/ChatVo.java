package com.mydic.chat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mydicchat")
public class ChatVo {
	@Id
	@GeneratedValue
	private Integer idx;
	private String id;
	private String content;
	@Column(insertable=false,  updatable=false, columnDefinition="date default sysdate")
	private Date writedate = new Date();
	
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "ChatVo [idx=" + idx + ", id=" + id + ", content=" + content + ", writedate=" + writedate + "]";
	}
	

	
	
}
