package com.senlainc.javacourses.petushokvaliantsin.dto.payment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
public class PaymentTypeDto implements Serializable {

    @Null(groups = Create.class)
    @Positive(groups = {Update.class, Read.class})
    @NotNull(groups = {Update.class, Read.class})
    private Long id;
    @NotEmpty(groups = {Create.class, Update.class})
    private String description;
    @NotNull(groups = {Create.class, Update.class})
    private Integer duration;
    @NotNull(groups = {Create.class, Update.class})
    private Double price;

    public interface Create {

    }

    public interface Update {

    }

    public interface Read {

    }
}
