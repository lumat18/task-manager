package com.spartez.mappers;

import com.spartez.domain.Column;
import com.spartez.model.ColumnDto;

public interface ColumnMapper {
    ColumnDto mapToColumnDto(final Column column);
    Column mapToColumn(final ColumnDto columnDto);
}
