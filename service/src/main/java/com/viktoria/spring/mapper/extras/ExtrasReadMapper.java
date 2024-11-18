package com.viktoria.spring.mapper.extras;

import com.viktoria.spring.database.entity.Extras;
import com.viktoria.spring.dto.extras.ExtrasReadDto;
import com.viktoria.spring.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtrasReadMapper implements Mapper<Extras, ExtrasReadDto> {

    @Override
    public ExtrasReadDto map(Extras object) {
        return new ExtrasReadDto(
                object.getId(),
                object.getName(),
                object.getDescription(),
                object.getImage(),
                object.getPrice()
        );
    }
}
