package com.viktoria.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SupFilter {
    String model;
    int numberSeats;
}
