package com.akqa;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author VenuNarukulla
 * This is a stand alone file to test in eclipse manually 
 */
@ComponentScan
@EnableAutoConfiguration
public class App {
	public static void main(String[] args) {

		String[] springConfig  = 
			{
				"spring/batch/config/context.xml",
				"spring/batch/jobs/job-meetingSchedule.xml" 
			};
		
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("meetingScheduleJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
		((AbstractApplicationContext) context).close();
		
		System.out.println("Job Completed.");

	}
}
