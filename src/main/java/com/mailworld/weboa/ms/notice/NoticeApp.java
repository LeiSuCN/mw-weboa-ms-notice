package com.mailworld.weboa.ms.notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NoticeApp {
  public static void main(String[] args) {
	  SpringApplication application = new SpringApplication(NoticeApp.class);
	  application.run(args);
  }
}
