package com.batch.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.batch.api.listener.JobListener;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.SimpleBinaryBufferedReaderFactory;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;



import com.batch.model.Employee;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
class BatchConfig {
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
/*	@SuppressWarnings("unchecked")
	@Bean
	FlatFileItemReader<String> reader() throws StreamReadException, DatabindException, IOException {
	    FlatFileItemReader<String> reader = new FlatFileItemReader<>();

	   // reader.setResource(new FileSystemResource("test.json"));

	    JsonLineMapper lineMapper = new JsonLineMapper();

	   // reader.setLineMapper(lineMapper);
	  
	    ObjectMapper mapper1 = new ObjectMapper();
	    mapper1.readValue(new File("src/main/resources/data.json"), String.class);
	    reader.setLineMapper((LineMapper<String>) mapper1);

	    return reader;
	}*/
	
	
/*	@Bean
	public FlatFileItemReader<String> reader() throws UnexpectedInputException, ParseException, Exception{
	

		FlatFileItemReader<String>  reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("10000SalesRecords.csv"));
		reader.setLineMapper(getLineMapper());
		//reader.setLinesToSkip(0);

	return reader;
	
	}

	private LineMapper<String> getLineMapper() {
		
	
		DefaultLineMapper<String> lineMapper = new DefaultLineMapper<>();
		
		//DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		//lineTokenizer.setNames(new String[] {"Region","Country","Item Type","Sales Channel"});
		//lineTokenizer.setIncludedFields(new int[] {0,1,2,3});
		
		BeanWrapperFieldSetMapper<String> fieldSetter = new BeanWrapperFieldSetMapper<>();
		
		fieldSetter.setTargetType(String.class);
		//lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetter);
		
		return lineMapper;
	}*/
	
	@Bean
	public EmployeeItemPocessor processor()
	{
		return new EmployeeItemPocessor();
	}
	
	/*@Bean
	public JdbcBatchItemWriter<Employee>  writer(){		
	JdbcBatchItemWriter<Employee>  writer = new JdbcBatchItemWriter<>();
	writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
	writer.setSql("INSERT INTO sales(region,country,itemType,salesChannel) VALUES(:region,:country,:itemType,:salesChannel)");
	System.out.println("writer============"+writer.toString());
	writer.setDataSource(datasource);
	return writer;
	}*/
	
	@Bean
	public Job importEmployeeJob(JobListenerExp listener) throws UnexpectedInputException, ParseException, Exception {
	
		return this.jobBuilderFactory.get("Employee-import-job..............................")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1())
				.end()
				.build();
		
	}
	
	@Bean
	public Step step1() throws UnexpectedInputException, ParseException, Exception {
		return this.stepBuilderFactory.get("step1")
		.<String,String>chunk(10)
		.reader(new Reader())
		.processor(processor())
		.writer(writer())
		.build();
			
	}
	
	@Bean
	  public ConsoleItemWriter<String> writer() 
	  {
	    return new ConsoleItemWriter<String>();
	  }
	

}
