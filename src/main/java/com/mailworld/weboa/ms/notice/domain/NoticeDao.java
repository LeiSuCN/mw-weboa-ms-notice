package com.mailworld.weboa.ms.notice.domain;

import java.util.List;

public interface NoticeDao {
	
	public List<Notice> queryByDepartment(int departmentCode);
	
	public int saveNotice(Notice notice);
	
	public NoticeInfo getInfoById(int noticeId);
	
	public List<NoticeItem> queryReceiversByNoticeId(int noticeId);
	
	public int saveReadRecord(int noticeId, int receiverId);
	
	public int incrementNoticeRead(int noticeId);
	
	public int saveNoticeStatistics(int noticeId);
	
	public int saveNoticeContent(NoticeItem content);
	
	public int saveNoticeAttachments(List<NoticeItem> attachments);
	
	public int saveNoticeReceivers(List<NoticeItem> receivers);
}
