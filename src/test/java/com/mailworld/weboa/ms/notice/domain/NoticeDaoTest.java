package com.mailworld.weboa.ms.notice.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mailworld.weboa.ms.notice.NoticeApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NoticeApp.class)
public class NoticeDaoTest {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;

	@Test
	public void test_01_suc_query_department() {
		
		int departmentCode = 100000;
		
		try(SqlSession session = this.sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			List<Notice> notices = noticeDao.queryByDepartment(departmentCode);			
			assertNotNull(notices);		
			assertTrue("数量", notices.size() > 0);
			
			notices.forEach( notice -> sysout(notice) );
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test_02_suc_save_notice() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("test_01_suc_save_notice");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);		
        TransactionStatus status = this.txManager.getTransaction(def);
		try(SqlSession session = this.sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			
			Notice notice = new Notice();
			notice.setTitle("测试" + System.currentTimeMillis());
			notice.setAuthor(6);
			notice.setValidity(30);
	
			int result = noticeDao.saveNotice(notice);
			assertTrue("插入数量", result == 1);
			assertTrue("公告ID", notice.getId() > 0);
			this.sysout(notice);
			
		} catch (Exception e) {
			fail(e.getMessage());
			this.txManager.rollback(status);
		}
		this.txManager.rollback(status);
		//this.txManager.commit(status);
		
	}

	@Test
	public void test_03_suc_get_noticeinfo() {
		try(SqlSession session = this.sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			NoticeInfo info = noticeDao.getInfoById(5);
			assertNotNull(info);
			assertNotNull(info.getNotice());
			this.sysout(info.getNotice());
			assertNotNull(info.getContent());
			this.sysout(info.getContent());
			assertNotNull(info.getAttachments());
			assertTrue("附件数量", info.getAttachments().size() > 0 );
			info.getAttachments().forEach( item -> this.sysout(item));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}

	@Test
	public void test_04_suc_query_Receivers() {
		try(SqlSession session = this.sqlSessionFactory.openSession()){
			NoticeDao noticeDao = session.getMapper(NoticeDao.class);
			List<NoticeItem> items = noticeDao.queryReceiversByNoticeId(5);
			assertNotNull(items);
			assertTrue("接受者数量", items.size() > 0 );
			items.forEach( item -> this.sysout(item));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}
	
	private void sysout(Notice notice){
		if( notice == null ){
			System.out.println("null");
		} else{
			System.out.println(
					"ID:" + notice.getId() + ", "
					+"Title:" + notice.getTitle() + ", "
					+"Category:" + notice.getCategory() + ", "
					+"Type:" + notice.getType() + ", "
					+"Create:" + notice.getCreate() + ", "
					+"Validity:" + notice.getValidity() + ", "
					);
		}
	}
	
	
	private void sysout(NoticeItem notice){
		if( notice == null ){
			System.out.println("null");
		} else{
			System.out.println(
					"ID:" + notice.getId() + ", "
					+"Notice:" + notice.getNoticeId() + ", "
					+"Value:" + notice.getValue() + ", "
					+"Create:" + notice.getCreate() + ", "
					+"Delete:" + notice.getDelete() + ", "
					);
		}
	}
}
