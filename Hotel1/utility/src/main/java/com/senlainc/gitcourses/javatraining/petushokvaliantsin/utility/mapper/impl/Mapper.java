package com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.impl;

import com.senlainc.gitcourses.javatraining.petushokvaliantsin.utility.mapper.IMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Component
public class Mapper implements IMapper {

    private final ModelMapper modelMapper;

    public Mapper() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
    }

    @Override
    public <D, T> D map(T entity, Class<D> outClazz) {
        return modelMapper.map(entity, outClazz);
    }

    @Override
    public <D, T> List<D> mapAll(List<T> entities, Class<D> outClazz) {
        return entities.stream().map(i -> map(i, outClazz)).collect(Collectors.toList());
    }
}
