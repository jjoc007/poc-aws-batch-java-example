package com.example.batch.config.jobs.multiply;

import com.example.batch.app.batch.MultiplyUseCaseImpl;
import com.example.batch.domain.MultiplyUseCase;
import java.util.Arrays;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiplyJobDefinition {

    @Bean
    public MultiplyUseCase multiplyUseCase() {
        return new MultiplyUseCaseImpl();
    }

    @Bean(name = "multiplyJob")
    public Job multiplyJob(
            JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        return jobBuilderFactory.get("multiplyJob")
                .start(multiplyStep(stepBuilderFactory))
                .build();
    }

    public Step multiplyStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("multiplyStep")
                .<Integer, Integer>chunk(1)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    public ListItemReader<Integer> itemReader() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        return new ListItemReader<>(data);
    }

    public ItemProcessor<Integer, Integer> itemProcessor() {
        return item -> multiplyUseCase().multiply(List.of(item), 2).get(0);
    }

    public ItemWriter<Integer> itemWriter() {
        return items -> items.forEach(System.out::println);
    }

}

