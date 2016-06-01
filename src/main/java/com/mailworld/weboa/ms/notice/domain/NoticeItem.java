package com.mailworld.weboa.ms.notice.domain;

public class NoticeItem {
	
	private int id;
	
	private int noticeId;
	
	private String value;
	
	private long create;
	
	private int delete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getCreate() {
		return create;
	}

	public void setCreate(long create) {
		this.create = create;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
}
