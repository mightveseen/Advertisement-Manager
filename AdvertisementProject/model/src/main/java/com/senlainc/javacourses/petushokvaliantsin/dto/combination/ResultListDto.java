package com.senlainc.javacourses.petushokvaliantsin.dto.combination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ResultListDto<E> {

    private Long listCounter;
    private List<E> resultList;

    public interface Create {

    }
}
