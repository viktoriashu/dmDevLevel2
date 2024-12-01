package com.viktoria.spring.mapper.claim;

import com.viktoria.spring.database.entity.Claim;
import com.viktoria.spring.dto.claim.ClaimReadDto;
import com.viktoria.spring.dto.sup.SupReadDto;
import com.viktoria.spring.dto.user.UserReadDto;
import com.viktoria.spring.mapper.Mapper;
import com.viktoria.spring.mapper.sup.SupReadMapper;
import com.viktoria.spring.mapper.user.UserReadMapper;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaimReadMapper implements Mapper<Claim, ClaimReadDto> {

    private final UserReadMapper userReadMapper;
    private final SupReadMapper supReadMapper;

    @Override
    public ClaimReadDto map(Claim object) {
        UserReadDto clientReadDto = Optional.ofNullable(object.getClient())
                .map(userReadMapper::map)
                .orElse(null);

        UserReadDto adminReadDto = Optional.ofNullable(object.getAdmin())
                .map(userReadMapper::map)
                .orElse(null);

        SupReadDto supReadDto = Optional.ofNullable(object.getSup())
                .map(supReadMapper::map)
                .orElse(null);

        return new ClaimReadDto(
                object.getId(),
                clientReadDto,
                adminReadDto,
                supReadDto,
                object.getDataStartRent(),
                object.getDurationRent(),
                object.getStatus(),
                object.getPrice()
                );
    }
}
