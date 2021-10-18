package com.lab.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lab.demo.listener.HWJobExecutionListener;
import com.lab.demo.listener.HWStepExecutionListener;
import com.lab.demo.processor.InMemitemProcessor;
import com.lab.demo.reader.InMemReader;
import com.lab.demo.writer.ConsoleItemWriter;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	private HWJobExecutionListener hwJobExecutionListener;
	
	@Autowired
	private HWStepExecutionListener hwStepExecutionListener;
	
	@Autowired
	private InMemitemProcessor inMemItemProcessor;
	
	@Bean
	public Step step1() {
		return steps.get("Step1")
				.listener(hwStepExecutionListener)
				.tasklet(helloWorldTasklet())
				.build();
	}
	
	@Bean
	public Step step2() {
		return steps.get("Step2").<Integer,Integer>chunk(3).reader(reader()).processor(inMemItemProcessor).writer(new ConsoleItemWriter()).build();
	}

	@Bean
	public ItemReader reader() {
		// TODO Auto-generated method stub
		return new InMemReader();
	}

	private Tasklet helloWorldTasklet() {
		
		return (new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
				System.out.println("Hello Spring Batch");
				return RepeatStatus.FINISHED;
			}
			
		});
	}
	
	@Bean
	public Job hellowWorldJob() {
		return jobs.get("helloWorldJob")
				.listener(hwJobExecutionListener)
				.start(step1())
				.next(step2())
				.build();
		
	}

}
