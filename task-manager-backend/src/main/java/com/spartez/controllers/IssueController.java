package com.spartez.controllers;

import com.spartez.domain.Column;
import com.spartez.domain.Issue;
import com.spartez.mappers.IssueMapper;
import com.spartez.model.IssueDto;
import com.spartez.services.ColumnService;
import com.spartez.services.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/issue")
public class IssueController {
    private final IssueService issueService;
    private final IssueMapper issueMapper;
    private final ColumnService columnService;

    public IssueController(IssueService issueService, IssueMapper issueMapper, ColumnService columnService) {
        this.issueService = issueService;
        this.issueMapper = issueMapper;
        this.columnService = columnService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueDto createIssue(@RequestBody final IssueDto issueDto){
        final Issue issue = issueMapper.mapToIssue(issueDto);
        final Issue createdIssue = issueService.create(issue);
        final Column column = columnService.getById(issueDto.getColumnIndex());
        column.getIssues().add(createdIssue);
        columnService.update(column);
        return issueMapper.mapToIssueDto(createdIssue);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IssueDto> getAllIssues(){
        return issueService.getAll().stream()
                .map(issueMapper::mapToIssueDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IssueDto getIssueById(@PathVariable("id") final Long id){
        return issueMapper.mapToIssueDto(issueService.getById(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public IssueDto updateIssue(@RequestBody final IssueDto issueDto){
        final Issue updatedIssue = issueService.update(issueMapper.mapToIssue(issueDto));
        columnService.getById(issueDto.getColumnIndex());
        return issueMapper.mapToIssueDto(updatedIssue);
    }

    @PutMapping("/move")
    @ResponseStatus(HttpStatus.OK)
    public void moveIssue(@RequestBody final IssueDto issueDto){
        final Issue issue = issueService.getById(issueDto.getId());
        issueService.moveToColumn(issue, issueDto.getColumnIndex(), issueDto.getDestColumnIndex());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIssue(@PathVariable("id") final Long id){
        issueService.delete(id);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(){
        return "Element was not found";
    }

}
