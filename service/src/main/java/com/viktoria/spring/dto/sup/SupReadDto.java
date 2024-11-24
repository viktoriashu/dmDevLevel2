package com.viktoria.spring.dto.sup;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class SupReadDto {
    Long id;
    String model;
    Integer numberSeats;
    String description;
    String image;
    BigDecimal price;
}
