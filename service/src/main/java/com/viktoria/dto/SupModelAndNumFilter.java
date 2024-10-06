package com.viktoria.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SupModelAndNumFilter {
    String model;
    int numberSeats;
}
