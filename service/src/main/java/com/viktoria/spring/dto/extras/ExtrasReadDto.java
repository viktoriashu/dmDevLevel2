package com.viktoria.spring.dto.extras;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class ExtrasReadDto {
    Long id;
    String name;
    String description;
    String image;
    BigDecimal price;
}
