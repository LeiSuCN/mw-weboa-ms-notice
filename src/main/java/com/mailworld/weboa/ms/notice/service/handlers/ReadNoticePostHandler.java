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
public class ReadNoticePostHandler {
	
	private final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;	
	
	@Subscribe
	public void handle(ReadNoticeEvent event){
		logger.debug("for notice ");
		NoticeInfo notice = event.getNotice();
		int noticeId = notice.getNotice().getId();
		int receiverId = event.getEmployeeId();
		try(SqlSession session = sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			noticeDao.saveReadRecord(noticeId, receiverId);
			noticeDao.incrementNoticeRead(noticeId);
		}
	}
	
	public static class ReadNoticeEvent{
		
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
