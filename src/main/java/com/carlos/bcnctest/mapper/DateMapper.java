package com.carlos.bcnctest.mapper;

import org.mapstruct.Mapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Mapper(componentModel = "spring")
public abstract class DateMapper {
    public OffsetDateTime dateToOffsetDate(Date date) {
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }
}
