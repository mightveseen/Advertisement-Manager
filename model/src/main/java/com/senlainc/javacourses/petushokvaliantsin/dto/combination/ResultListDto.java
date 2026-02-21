package com.senlainc.javacourses.petushokvaliantsin.dto.combination;

import java.util.List;

public record ResultListDto<E>(
        Long listCounter,
        List<E> resultList
) {

    public static <E> ResultListDto<E> of(Long listCounter, List<E> resultList) {
        return new ResultListDto<>(listCounter, resultList);
    }

    public interface Create {
    }
}
