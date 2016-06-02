package com.mailworld.weboa.ms.notice;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.eventbus.EventBus;

@Configuration
public class NoticeConfigration {
	
	@Value("${mw.db.url}")
	private String dbUrl;
	
	@Value("${mw.db.username}")
	private String dbUsername;
	
	@Value("${mw.db.password}")
	private String dbPassword;
	
	@Value("${mw.db.driver}")
	private String dbDriver;
	
	
	@Bean
	public DataSource getDataSource(){		
		PooledDataSource ds = new PooledDataSource();
		ds.setUrl(this.dbUrl);
		ds.setUsername(this.dbUsername);
		ds.setPassword(this.dbPassword);
		ds.setDriver(this.dbDriver);
		ds.setPoolPingEnabled(true);
		ds.setPoolPingQuery("select 1");
		return ds;
	}
	
	@Bean
	public EventBus getNoticeEventBus(){
		EventBus eb = new EventBus("eb-notice");
		return eb;
	}
}
