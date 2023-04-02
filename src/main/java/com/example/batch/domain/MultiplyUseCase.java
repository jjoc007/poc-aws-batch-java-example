package com.example.batch.domain;

import java.util.List;

public interface MultiplyUseCase {
    List<Integer> multiply(List<Integer> numbers, int factor);
}
