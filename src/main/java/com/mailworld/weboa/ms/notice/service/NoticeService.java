package com.mailworld.weboa.ms.notice.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.eventbus.EventBus;
import com.mailworld.weboa.ms.notice.domain.NoticeDao;
import com.mailworld.weboa.ms.notice.domain.NoticeInfo;

@Service
public class NoticeService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private EventBus bus;
	
	public NoticeInfo getNoticeInfoByIdFromEmployee(int noticeId, int employeeId){
		
		NoticeInfo info = null;
		try(SqlSession session = sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			info = noticeDao.getInfoById(noticeId);
			
			if( info != null ){
				// 统计读取时间 TODO
				bus.register(this);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return info;
	}

}
