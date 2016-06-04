package com.mailworld.weboa.ms.notice.service.handlers;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.mailworld.weboa.ms.notice.domain.NoticeDao;
import com.mailworld.weboa.ms.notice.domain.NoticeInfo;

@Component
public class PublishNoticePostHandler {
	
	private final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;	
	
	@Subscribe
	public void handle(PublishNoticeEvent event){
		logger.debug("publish notice");
		NoticeInfo notice = event.getNotice();
		int noticeId = notice.getNotice().getId();
		try(SqlSession session = sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			noticeDao.saveNoticeStatistics(noticeId);
		}
	}
	
	public static class PublishNoticeEvent{
		
		private NoticeInfo notice;
		
		private int employeeId;

		public NoticeInfo getNotice() {
			return notice;
		}

		public void setNotice(NoticeInfo notice) {
			this.notice = notice;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
	}
}
