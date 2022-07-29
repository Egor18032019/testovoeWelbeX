package com.welbex.testovoe.factories;

import com.welbex.testovoe.dto.TableDto;
import com.welbex.testovoe.strore.entities.TableEntities;

public class TableDtoFactories {
    public static TableDto makeTable (TableEntities entity){
        return TableDto.builder()
                .date(entity.getDate())
                .name(entity.getName())
                .quantity(entity.getQuantity())
                .distance(entity.getDistance())
                 .build();
    }
}
