package com.example.batch.app.batch;

import com.example.batch.domain.MultiplyUseCase;
import java.util.List;
import java.util.stream.Collectors;


public class MultiplyUseCaseImpl implements MultiplyUseCase {
    @Override
    public List<Integer> multiply(List<Integer> numbers, int factor) {
        return numbers.stream()
                .map(number -> number * factor)
                .collect(Collectors.toList());
    }
}