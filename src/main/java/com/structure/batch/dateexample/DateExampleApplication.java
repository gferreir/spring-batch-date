package com.structure.batch.dateexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

@EnableBatchProcessing
@SpringBootApplication
public class DateExampleApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step() {
		return this.stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter dateTimeForm = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				System.out.println("Date Time: " + dateTime.format(dateTimeForm));
				
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Job job() {
		return this.jobBuilderFactory.get("job").start(step()).build();
	}
	public static void main(String[] args) {
		SpringApplication.run(DateExampleApplication.class, args);
	}

}
