package com.viktoria.spring.dto.sup;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SupFilter {
    String model;
    Integer numberSeats;
}
