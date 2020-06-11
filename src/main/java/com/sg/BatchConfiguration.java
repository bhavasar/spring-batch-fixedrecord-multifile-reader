package com.sg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sg.poc.model.ContractBandGuaranteed;
import com.sg.poc.model.ContractRecord;
import com.sg.poc.model.ContractUnderlyingAssets;
import com.sg.poc.model.ContractValuationRecord;
import com.sg.poc.model.SubmittingHeader;
import com.spg.batch.components.ContraRecordFieldSetMapper;
import com.spg.batch.components.PVFPMFFileReader;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JdbcBatchItemWriter<Person> writer;

	@Autowired
	private FlatFileItemReader<Person> personItemReader;
	
	@Autowired
	private PVFPMFFileReader povItemReader;

	@Bean("partitioner")
	@StepScope
	public Partitioner partitioner() {
		log.info("In Partitioner");

		MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver.getResources("/customer*.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		partitioner.setResources(resources);
		partitioner.partition(10);
		return partitioner;
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
				.dataSource(dataSource)
				.build();
	}

	@Bean
	public ItemWriter itemWriter() {
		return (items) -> {
			System.out.println("Results are here: in nested object");
			items.forEach(System.out::println);
			
		};
	}
	
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(masterStep())
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<SubmittingHeader, SubmittingHeader>chunk(1)
			//	.processor(processor())
				.writer(itemWriter())
				.reader(povItemReader)
				.build();
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

	@Bean
	@Qualifier("masterStep")
	public Step masterStep() {
		return stepBuilderFactory.get("masterStep")
				.partitioner("step1", partitioner())
				.step(step1())
				.taskExecutor(taskExecutor())
				.build();
	}

	@Bean
	@StepScope
	@DependsOn("partitioner")
	public FlatFileItemReader customerItemReader(@Value("#{stepExecutionContext['fileName']}") String filename) throws MalformedURLException {

		return new FlatFileItemReaderBuilder<SubmittingHeader>()
				.name("customerItemReader")
				.lineMapper(lineTokenizer())
				.resource(new UrlResource(filename))
				.build();
	}
	


	@Bean
	@StepScope
	@Qualifier("povItemReader")
	@DependsOn("partitioner")
	public PVFPMFFileReader customerFileReader(@Value("#{stepExecutionContext['fileName']}") String filename) throws MalformedURLException {
		return new PVFPMFFileReader(customerItemReader(filename));
	}
	
	/*
	@Bean
	@StepScope
	@Qualifier("personItemReader")
	@DependsOn("partitioner")
	public FlatFileItemReader<Person> personItemReader(@Value("#{stepExecutionContext['fileName']}") String filename)
			throws MalformedURLException {
		log.info("In Reader");
		return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
				.delimited()
				.names(new String[] { "firstName", "lastName" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				})
				.resource(new UrlResource(filename))
				.build();
	}
	*/
	
	
	@Bean
	public PatternMatchingCompositeLineMapper lineTokenizer() {
		Map<String, LineTokenizer> lineTokenizers =
				new HashMap<>(2);

		lineTokenizers.put("B10*", customerLineTokenizer());
		lineTokenizers.put("C15*", transactionLineTokenizer());
		lineTokenizers.put("C1301*", contractRecordLineTokenizer());
		lineTokenizers.put("C1302*", contractValuationRecordLineTokenizer());
		lineTokenizers.put("C1303*", contractUnderlyingAssetLineTokenizer());
		lineTokenizers.put("C1304*", contractBandGuaranteeLineTokenizer());
		//lineTokenizers.put("C1304*", contractAgentRecordLineTokenizer());
		
		Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();

		BeanWrapperFieldSetMapper<SubmittingHeader> customerFieldSetMapper =
				new BeanWrapperFieldSetMapper<>();
		customerFieldSetMapper.setTargetType(SubmittingHeader.class);

		BeanWrapperFieldSetMapper<ContractRecord> contractRecordFieldSetMapper =
				new BeanWrapperFieldSetMapper<>();
		contractRecordFieldSetMapper.setTargetType(ContractRecord.class);
		
		BeanWrapperFieldSetMapper<ContractValuationRecord> contractValuationRecordFieldSetMapper =
				new BeanWrapperFieldSetMapper<>();
		contractValuationRecordFieldSetMapper.setTargetType(ContractValuationRecord.class);
		
		
		BeanWrapperFieldSetMapper<ContractUnderlyingAssets> contractUndrlyingAssetFieldSetMapper =
		new BeanWrapperFieldSetMapper<>();
		contractUndrlyingAssetFieldSetMapper.setTargetType(ContractUnderlyingAssets.class);
		
		
		BeanWrapperFieldSetMapper<ContractBandGuaranteed> contractBandGuranteedFieldSetMapper =
				new BeanWrapperFieldSetMapper<>();
		contractBandGuranteedFieldSetMapper.setTargetType(ContractBandGuaranteed.class);
				
		fieldSetMappers.put("B10*", customerFieldSetMapper);
		fieldSetMappers.put("C15*", new ContraRecordFieldSetMapper());
		fieldSetMappers.put("C1301*", contractRecordFieldSetMapper);
		fieldSetMappers.put("C1302*", contractValuationRecordFieldSetMapper);
		fieldSetMappers.put("C1303*", contractUndrlyingAssetFieldSetMapper);
		fieldSetMappers.put("C1304*", contractBandGuranteedFieldSetMapper);
		
		
		PatternMatchingCompositeLineMapper lineMappers =
				new PatternMatchingCompositeLineMapper();

		lineMappers.setTokenizers(lineTokenizers);
		lineMappers.setFieldSetMappers(fieldSetMappers);

		return lineMappers;
	}
	
	
	
	@Bean
	public FixedLengthTokenizer contractAgentRecordLineTokenizer() {
		FixedLengthTokenizer fixLenToenizer = new FixedLengthTokenizer();
		fixLenToenizer.setNames(new String[] {"submitterCode", "recordType", "sequenceNumber",
			"contractNumber", "agentTaxId" });
		Range[] ranges = new Range[]{new Range(1,1), new Range(2, 3), new Range(4, 5),
				new Range(6, 35), new Range(36,55)};
		fixLenToenizer.setColumns(ranges);
		return fixLenToenizer;
	}
	
	
	@Bean
	public FixedLengthTokenizer contractBandGuaranteeLineTokenizer() {
		FixedLengthTokenizer fixLenToenizer = new FixedLengthTokenizer();
		fixLenToenizer.setNames(new String[] {"submitterCode", "recordType", "sequenceNumber",
			"contractNumber", "cusip", "depositGuaranteedStartDate" });
		Range[] ranges = new Range[]{new Range(1,1), new Range(2, 3), new Range(4, 5),
				new Range(6, 35), new Range(36,54), new Range(55, 62)};
		fixLenToenizer.setColumns(ranges);
		return fixLenToenizer;
	}
	
	@Bean
	public FixedLengthTokenizer contractUnderlyingAssetLineTokenizer() {
		FixedLengthTokenizer fixLenToenizer = new FixedLengthTokenizer();
		fixLenToenizer.setNames(new String[] {"submitterCode", "recordType", "sequenceNumber",
			"contractNumber", "cusip" });
		Range[] ranges = new Range[]{new Range(1,1), new Range(2, 3), new Range(4, 5),
				new Range(6, 35), new Range(36,54)};
		fixLenToenizer.setColumns(ranges);
		return fixLenToenizer;
	}
	
	@Bean
	public FixedLengthTokenizer contractValuationRecordLineTokenizer() {
		FixedLengthTokenizer fixLenToenizer = new FixedLengthTokenizer();
		fixLenToenizer.setNames(new String[] {"submitterCode", "recordType", "sequenceNumber",
			"contractNumber", "contractValueAmount1", "contractValueQualifier1","contractValueAmount2", "contractValueQualifier2" });
		Range[] ranges = new Range[]{new Range(1,1), new Range(2, 3), new Range(4, 5),
				new Range(6, 35), new Range(36,51), new Range(52,54), new Range(56,71), new Range(72,74)};
		fixLenToenizer.setColumns(ranges);
		return fixLenToenizer;
	}
	
	@Bean
	public FixedLengthTokenizer contractRecordLineTokenizer() {
		FixedLengthTokenizer fixLenToenizer = new FixedLengthTokenizer();
		fixLenToenizer.setNames(new String[] {"submitterCode", "recordType", "sequenceNumber",
			"contractNumber", "cusip"});
		Range[] ranges = new Range[]{new Range(1,1), new Range(2, 3), new Range(4, 5),
				new Range(6, 35), new Range(36,44)};
		fixLenToenizer.setColumns(ranges);
		return fixLenToenizer;
	}
	@Bean
	public DelimitedLineTokenizer transactionLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer =
				new DelimitedLineTokenizer();

		lineTokenizer.setNames("prefix",
				"accountNumber",
				"transactionDate",
				"amount");

		return lineTokenizer;
	}

	@Bean
	public DelimitedLineTokenizer customerLineTokenizer() {
		DelimitedLineTokenizer lineTokenizer =
				new DelimitedLineTokenizer();

		lineTokenizer.setNames("firstName",
				"middleInitial",
				"lastName",
				"address",
				"city",
				"state",
				"zipCode");

		lineTokenizer.setIncludedFields(1, 2, 3, 4, 5, 6, 7);

		return lineTokenizer;
	}

}
