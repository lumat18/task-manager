package com.spartez.controllers;

import com.spartez.domain.Column;
import com.spartez.mappers.ColumnMapper;
import com.spartez.model.ColumnDto;
import com.spartez.services.ColumnService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/column")
public class ColumnController {
    private final ColumnService columnService;
    private final ColumnMapper columnMapper;

    public ColumnController(ColumnService columnService, @Qualifier("columnMapperImpl") ColumnMapper columnMapper) {
        this.columnService = columnService;
        this.columnMapper = columnMapper;
    }

    @PostMapping
    public ColumnDto createColumn(final ColumnDto columnDto){
        final Column column = columnMapper.mapToColumn(columnDto);
        final Column createdColumn = columnService.create(column);
        return columnMapper.mapToColumnDto(createdColumn);
    }

    @GetMapping
    public List<ColumnDto> getAllColumns(){
        return columnService.getAll().stream()
                .map(columnMapper::mapToColumnDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ColumnDto getColumnById(@PathVariable("id") final Long id){
        return columnMapper.mapToColumnDto(columnService.getById(id));
    }

    @PutMapping
    public ColumnDto updateColumn(final ColumnDto columnDto){
        final Column updatedColumn = columnService.update(columnMapper.mapToColumn(columnDto));
        return columnMapper.mapToColumnDto(updatedColumn);
    }

    @DeleteMapping("/{id")
    public void deleteColumn(@PathVariable("id") final Long id){
        columnService.delete(id);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException() {
        return "Element was not found";
    }
}
