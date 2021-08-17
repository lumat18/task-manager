package com.spartez.controllers;

import com.spartez.domain.Column;
import com.spartez.domain.Issue;
import com.spartez.mappers.ColumnMapper;
import com.spartez.mappers.IssueMapper;
import com.spartez.model.ColumnDto;
import com.spartez.model.IssueDto;
import com.spartez.services.ColumnService;
import com.spartez.services.IssueService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/column")
public class ColumnController {
    private final ColumnService columnService;
    private final ColumnMapper columnMapper;
    private final IssueMapper issueMapper;
    private final IssueService issueService;

    public ColumnController(ColumnService columnService, @Qualifier("columnMapperImpl") ColumnMapper columnMapper, @Qualifier("issueMapperImpl") IssueMapper issueMapper, IssueService issueService) {
        this.columnService = columnService;
        this.columnMapper = columnMapper;
        this.issueMapper = issueMapper;
        this.issueService = issueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ColumnDto createColumn(final ColumnDto columnDto){
        final Column column = columnMapper.mapToColumn(columnDto);
        final Column createdColumn = columnService.create(column);
        return columnMapper.mapToColumnDto(createdColumn);
    }

    @PostMapping("/{id}/add-issue")
    @ResponseStatus(HttpStatus.CREATED)
    public IssueDto createIssue(@PathVariable("id") final Long id, @RequestBody final IssueDto issueDto){
        final Issue issue = issueMapper.mapToIssue(issueDto);
        final Issue newIssue = issueService.create(issue);
        final Column column = columnService.getById(id);
        column.getIssues().add(newIssue);
        columnService.update(column);
        return issueMapper.mapToIssueDto(newIssue);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ColumnDto> getAllColumns(){
        return columnService.getAll().stream()
                .map(columnMapper::mapToColumnDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColumnDto getColumnById(@PathVariable("id") final Long id){
        return columnMapper.mapToColumnDto(columnService.getById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ColumnDto updateColumn(@RequestBody final ColumnDto columnDto){
        final Column updatedColumn = columnService.update(columnMapper.mapToColumn(columnDto));
        return columnMapper.mapToColumnDto(updatedColumn);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteColumn(@PathVariable("id") final Long id){
        columnService.delete(id);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException() {
        return "Element was not found";
    }
}
