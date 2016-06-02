package com.mailworld.weboa.ms.notice;

import com.mailworld.weboa.ms.notice.domain.Notice;
import com.mailworld.weboa.ms.notice.domain.NoticeItem;

public class SysoutUtils {
	
	public static void sysout(Notice notice){
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
	
	
	public static void sysout(NoticeItem notice){
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
