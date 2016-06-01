package com.mailworld.weboa.ms.notice.domain;

public class Notice {
	
	private int id;

	private String title;
	
	private int author;
	
	private int category;
	
	private int type;
	
	private int validity;
	
	private long create;
	
	private int delete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
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
}
