package com.senlainc.javacourses.petushokvaliantsin.configuration.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public final class ExceptionTemplate {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss+dd-MM-yyyy")
    private LocalDateTime dateTime;
    private String message;

    private ExceptionTemplate(String message) {
        this.dateTime = LocalDateTime.now();
        this.message = message;
    }

    public static ExceptionTemplate of(String message) {
        return new ExceptionTemplate(message);
    }
}
