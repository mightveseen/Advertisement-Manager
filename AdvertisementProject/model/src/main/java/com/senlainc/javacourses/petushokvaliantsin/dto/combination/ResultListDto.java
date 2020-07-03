package com.senlainc.javacourses.petushokvaliantsin.dto.combination;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ResultListDto<E> {

    private Long listCounter;
    private List<E> resultList;

    public ResultListDto(@NotNull(groups = Create.class) Long listCounter, @NotNull(groups = Create.class) List<E> resultList) {
        this.listCounter = listCounter;
        this.resultList = resultList;
    }

    public Long getListCounter() {
        return listCounter;
    }

    public void setListCounter(Long listCounter) {
        this.listCounter = listCounter;
    }

    public List<E> getResultList() {
        return resultList;
    }

    public void setResultList(List<E> resultList) {
        this.resultList = resultList;
    }

    public interface Create {

    }
}
