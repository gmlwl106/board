package com.javaex.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
	
	@Scheduled(cron = "0 0 17 * * *")
	public void doJob() {
		System.out.println("스케줄링 테스트");
	}
}
