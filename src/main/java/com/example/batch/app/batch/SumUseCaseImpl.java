package com.example.batch.app.batch;

import com.example.batch.domain.SumUseCase;
import java.util.List;

public class SumUseCaseImpl implements SumUseCase {
    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
