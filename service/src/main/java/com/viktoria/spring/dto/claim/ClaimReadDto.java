package com.viktoria.spring.dto.claim;

import com.viktoria.spring.database.entity.Extras;
import com.viktoria.spring.database.entity.Status;
import com.viktoria.spring.dto.extras.ExtrasReadDto;
import com.viktoria.spring.dto.sup.SupReadDto;
import com.viktoria.spring.dto.user.UserReadDto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Value;

@Value
public class ClaimReadDto {
    Long id;
    UserReadDto clientReadDto;
    UserReadDto adminReadDto;
    SupReadDto supReadDto;
//    ExtrasReadDto extrasReadDto;
    LocalDate dataStartRent;
    Integer durationRent;
    Status status;
    BigDecimal price;

}
