package com.spartez.controllers;

import com.spartez.domain.Issue;
import com.spartez.mappers.IssueMapper;
import com.spartez.model.IssueDto;
import com.spartez.services.IssueService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/issue")
public class IssueController {
    private final IssueService issueService;
    private final IssueMapper issueMapper;

    public IssueController(IssueService issueService, @Qualifier("issueMapperImpl") IssueMapper issueMapper) {
        this.issueService = issueService;
        this.issueMapper = issueMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueDto createIssue(final IssueDto issueDto){
        final Issue issue = issueMapper.mapToIssue(issueDto);
        Issue createdIssue = issueService.create(issue);
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
    public IssueDto updateIssue(@PathVariable("id") final IssueDto issueDto){
        final Issue updatedIssue = issueService.update(issueMapper.mapToIssue(issueDto));
        return issueMapper.mapToIssueDto(updatedIssue);
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