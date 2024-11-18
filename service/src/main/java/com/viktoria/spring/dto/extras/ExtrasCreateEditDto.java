package com.viktoria.spring.dto.extras;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

@Value
@FieldNameConstants
public class ExtrasCreateEditDto {

    @NotBlank
    String name;

    @NotBlank
    @Size(min = 5, max = 500)
    String description;

    MultipartFile image;

    @DecimalMin("0.0")
    BigDecimal price;
}
