package com.mailworld.weboa.ms.notice.domain;

import java.util.List;

public interface NoticeDao {
	
	public List<Notice> queryByDepartment(int departmentCode);
	
	public int saveNotice(Notice notice);
	
	public NoticeInfo getInfoById(int noticeId);
	
	public List<NoticeItem> queryReceiversByNoticeId(int noticeId);

}
