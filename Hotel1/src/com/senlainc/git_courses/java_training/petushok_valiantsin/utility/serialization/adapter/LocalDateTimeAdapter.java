package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String v) {
        return LocalDateTime.parse(v);
    }

    @Override
    public String marshal(LocalDateTime v) {
        return v.toString();
    }
}
