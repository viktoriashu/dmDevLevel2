package com.viktoria.spring.dto.sup;

import jakarta.persistence.Column;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SupFilter {
    String model;
    Integer numberSeats;

//нужно как-то использовать для фильтра
//    LocalDate dataStartRent;
//    int durationRent;
}
