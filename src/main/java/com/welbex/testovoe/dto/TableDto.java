package com.welbex.testovoe.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableDto {
    @NonNull
    private LocalDate date;
    @NonNull
    String name;
    @NonNull
    int quantity;
    @NonNull
    Long distance;
}
