package com.mailworld.weboa.ms.notice.web.dto;

import java.util.List;

public class DtoNotice {
	
	private String title;

	private String content;
	
	private int validity;
	
	private int category;
	
	private int type;
	
	private List<DtoNoticeItem> attachments;
	
	private List<DtoNoticeItem> receivers;
	
	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public List<DtoNoticeItem> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<DtoNoticeItem> attachments) {
		this.attachments = attachments;
	}

	public List<DtoNoticeItem> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<DtoNoticeItem> receivers) {
		this.receivers = receivers;
	}

	public static class DtoNoticeItem {
		private String name;
		private String value;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
