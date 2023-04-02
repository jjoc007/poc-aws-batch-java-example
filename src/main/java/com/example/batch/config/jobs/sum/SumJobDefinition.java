package com.example.batch.config.jobs.sum;

import com.example.batch.app.batch.SumUseCaseImpl;
import com.example.batch.domain.SumUseCase;
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
public class SumJobDefinition {

    @Bean
    public SumUseCase sumUseCase() {
        return new SumUseCaseImpl();
    }

    @Bean(name = "sumJob")
    public Job sumJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        return jobBuilderFactory.get("sumJob")
                .start(sumStep(stepBuilderFactory))
                .build();
    }

    public Step sumStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("sumStep")
                .<List<Integer>, Integer>chunk(1)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    public ListItemReader<List<Integer>> itemReader() {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        return new ListItemReader<>(Arrays.asList(data));
    }

    public ItemProcessor<List<Integer>, Integer> itemProcessor() {
        return item -> sumUseCase().sum(item);
    }

    public ItemWriter<Integer> itemWriter() {
        return items -> items.forEach(System.out::println);
    }
}

