package com.viktoria.spring.mapper.sup;

import com.viktoria.spring.database.entity.Sup;
import com.viktoria.spring.dto.sup.SupReadDto;
import com.viktoria.spring.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupReadMapper implements Mapper<Sup, SupReadDto> {

    @Override
    public SupReadDto map(Sup object) {
        return new SupReadDto(
                object.getId(),
                object.getModel(),
                object.getNumberSeats(),
                object.getDescription(),
                object.getImage(),
                object.getPrice()
        );
    }
}
