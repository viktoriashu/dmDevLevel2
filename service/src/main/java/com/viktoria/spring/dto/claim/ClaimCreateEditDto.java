package com.viktoria.spring.dto.claim;

import com.viktoria.spring.database.entity.ExtrasClaim;
import com.viktoria.spring.database.entity.Status;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Builder
@Value
@FieldNameConstants
public class ClaimCreateEditDto {

//    Long clientId;
    Long adminId;
    Long supId;
    LocalDate dataStartRent;
    Integer durationRent;
    Status status;
    BigDecimal price;
}
