package com.mailworld.weboa.ms.notice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.google.common.eventbus.EventBus;
import com.mailworld.weboa.ms.notice.domain.Notice;
import com.mailworld.weboa.ms.notice.domain.NoticeDao;
import com.mailworld.weboa.ms.notice.domain.NoticeInfo;
import com.mailworld.weboa.ms.notice.domain.NoticeItem;
import com.mailworld.weboa.ms.notice.service.handlers.PublishNoticePostHandler;
import com.mailworld.weboa.ms.notice.service.handlers.ReadNoticePostHandler;

@Service
public class NoticeService {
	
	private final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	@Autowired
	private PublishNoticePostHandler publishNoticePostHandler;
	
	@Autowired
	private ReadNoticePostHandler readNoticePostHandler;
	
	private EventBus bus;
	
	public NoticeInfo publishNoticeInfo(NoticeInfo info){
		Notice notice = info.getNotice();
		TransactionStatus t = this.begin("public_noticeinfo_" + System.currentTimeMillis());
		try(SqlSession session = sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			noticeDao.saveNotice(notice);
			int noticeId = notice.getId();
			if( noticeId > 0 ){
				List<NoticeItem> attachments = info.getAttachments();
				if( null != attachments && attachments.size() > 0){
					attachments.forEach( attachment -> attachment.setNoticeId(noticeId) );
					noticeDao.saveNoticeAttachments(attachments);
				}
				
				NoticeItem content = info.getContent();
				if( null != content ){
					content.setNoticeId(noticeId);
					noticeDao.saveNoticeContent(content);
				}
				
				List<NoticeItem> receivers = info.getReceivers();
				if( null != receivers && receivers.size() > 0){
					receivers.forEach( item -> item.setNoticeId(noticeId) );
					noticeDao.saveNoticeReceivers(receivers);
				}
			}
			this.commit(t);
			PublishNoticePostHandler.PublishNoticeEvent event
			  = new PublishNoticePostHandler.PublishNoticeEvent();
			event.setNotice(info);
			this.bus.post(event);
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.rollback(t);
		}
		return info;
	}

	public NoticeInfo getNoticeInfoByIdFromEmployee(int noticeId, int employeeId){
		
		NoticeInfo info = null;
		try(SqlSession session = sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			info = noticeDao.getInfoById(noticeId);
			if( info != null ){
				ReadNoticePostHandler.ReadNoticeEvent event =
				  new ReadNoticePostHandler.ReadNoticeEvent();
				event.setNotice(info);
				event.setEmployeeId(employeeId);
				this.bus.post(event);
			}			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return info;
	}

	@Autowired
	@DependsOn("ReadNoticePostHandler")
	public void setBus(EventBus bus) {
		logger.info("register eventbus");
		this.bus = bus;
		this.bus.register(this.readNoticePostHandler);
		this.bus.register(this.publishNoticePostHandler);
	}
	
	private TransactionStatus begin(String transactionName){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName(transactionName);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);		
        TransactionStatus status = this.txManager.getTransaction(def);
        return status;
	}
	
	private void commit(TransactionStatus status){
		this.txManager.commit(status);
	}
	
	private void rollback(TransactionStatus status){
		this.txManager.rollback(status);
	}
}
