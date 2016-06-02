package com.mailworld.weboa.ms.notice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mailworld.weboa.ms.notice.NoticeApp;
import com.mailworld.weboa.ms.notice.SysoutUtils;
import com.mailworld.weboa.ms.notice.domain.NoticeInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NoticeApp.class)
public class NoticeServiceTest {
	
	@Autowired
	private NoticeService service;

	@Test
	public void test_01_succ_getInfo() {
		
		NoticeInfo info = this.service.getNoticeInfoByIdFromEmployee(5, -1);
		assertNotNull(info);
		assertNotNull(info.getNotice());
		SysoutUtils.sysout(info.getNotice());
		assertNotNull(info.getContent());
		SysoutUtils.sysout(info.getContent());
		assertNotNull(info.getAttachments());
		assertTrue("附件数量", info.getAttachments().size() > 0 );
		info.getAttachments().forEach( item -> SysoutUtils.sysout(item));
	}

}
