package com.viktoria.spring.dto.sup;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Value
@FieldNameConstants
public class SupCreateEditDto {

    @NotBlank
    String model;

    @Positive
    @Min(1)
    @Max(20)
    Integer numberSeats;

    @NotBlank
    @Size(min = 5, max = 500)
    String description;

    MultipartFile image;

    @DecimalMin("0.0")
    BigDecimal price;
}
