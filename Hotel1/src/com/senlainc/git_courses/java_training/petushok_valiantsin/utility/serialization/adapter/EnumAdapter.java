package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.serialization.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EnumAdapter extends XmlAdapter<String, Enum> {
    @Override
    public Enum unmarshal(String v) {
        return null;
    }

    @Override
    public String marshal(Enum v) {
        return null;
    }
}
