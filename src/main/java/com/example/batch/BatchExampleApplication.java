package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.JobParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class BatchExampleApplication {

	public static void main(String[] args) throws Exception  {
		ConfigurableApplicationContext context = SpringApplication.run(BatchExampleApplication.class, args);

		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		String jobName = args.length > 0 ? args[0] : "";

		Job selectedJob;
		if ("sumJob".equalsIgnoreCase(jobName)) {
			selectedJob = context.getBean("sumJob", Job.class);
		} else if ("multiplyJob".equalsIgnoreCase(jobName)) {
			selectedJob = context.getBean("multiplyJob", Job.class);
		} else {
			System.err.println("Invalid job name provided. Please use 'sumJob' or 'multiplyJob'.");
			context.close();
			return;
		}

		jobLauncher.run(selectedJob, new JobParameters());
		context.close();
	}

}
