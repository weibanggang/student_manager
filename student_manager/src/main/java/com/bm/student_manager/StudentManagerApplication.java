package com.bm.student_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

@EnableAsync(proxyTargetClass = true)
@EnableScheduling
//开启数据库事务
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com")
@MapperScan(basePackages = "com.bm.student_manager.dao")
public class StudentManagerApplication extends SpringBootServletInitializer implements WebApplicationInitializer, ApplicationListener<ContextRefreshedEvent> {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(com.bm.student_manager.StudentManagerApplication.class);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		SpringContextUtil.setApplicationContext(contextRefreshedEvent.getApplicationContext());
	}
	public static void main(String[] args) {
		SpringApplication.run(com.bm.student_manager.StudentManagerApplication.class, args);
	}

}
