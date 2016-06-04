package com.mailworld.weboa.ms.notice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mailworld.weboa.ms.notice.NoticeApp;
import com.mailworld.weboa.ms.notice.SysoutUtils;
import com.mailworld.weboa.ms.notice.domain.Notice;
import com.mailworld.weboa.ms.notice.domain.NoticeInfo;
import com.mailworld.weboa.ms.notice.domain.NoticeItem;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NoticeApp.class)
public class NoticeServiceTest {
	
	@Autowired
	private NoticeService service;

	@Test
	public void test_01_succ_getInfo() {
		
		NoticeInfo info = this.service.getNoticeInfoByIdFromEmployee(5, 6);
		assertNotNull(info);
		assertNotNull(info.getNotice());
		SysoutUtils.sysout(info.getNotice());
		assertNotNull(info.getContent());
		SysoutUtils.sysout(info.getContent());
		assertNotNull(info.getAttachments());
		assertTrue("附件数量", info.getAttachments().size() > 0 );
		info.getAttachments().forEach( item -> SysoutUtils.sysout(item));
	}

	@Test
	public void test_02_succ_saveInfo() {
		NoticeInfo info = new NoticeInfo();
		
		Notice notice = new Notice();
		notice.setTitle("测试" + System.currentTimeMillis());
		notice.setAuthor(6);
		notice.setValidity(30);
		info.setNotice(notice);
		
		NoticeItem item = null;
		List<NoticeItem> attachments = new ArrayList<>();
		item = new NoticeItem();
		item.setName("文档附件");
		item.setValue("http://document.com");
		attachments.add(item);
		item = new NoticeItem();
		item.setName("图片附件");
		item.setValue("http://picture.com");
		attachments.add(item);		
		info.setAttachments(attachments);
		
		item = new NoticeItem();
		item.setValue("Hello World");
		info.setContent(item);
		
		List<NoticeItem> receivers = new ArrayList<>();
		item = new NoticeItem();
		item.setValue("2000000");
		item.setName("1");
		receivers.add(item);
		item = new NoticeItem();
		item.setValue("2000016");
		item.setName("2");
		receivers.add(item);		
		info.setReceivers(receivers);

		this.service.publishNoticeInfo(info);
		
		assertTrue("生成ID", notice.getId() > 0);
		System.out.println(notice.getId());
	}
}
