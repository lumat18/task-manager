package com.spartez.services;

import com.spartez.domain.Column;
import com.spartez.domain.Issue;
import com.spartez.repositories.ColumnRepository;
import com.spartez.repositories.IssueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class IssueService {
    private final IssueRepository issueRepository;
    private final ColumnRepository columnRepository;

    public IssueService(IssueRepository issueRepository, ColumnRepository columnRepository) {
        this.issueRepository = issueRepository;
        this.columnRepository = columnRepository;
    }

    public Issue create(final Issue issue){
        log.info("Issue entitled: " + issue.getTitle() + " created");
        return issueRepository.save(issue);
    }

    public Collection<Issue> getAll(){
        log.info("All issues requested");
        return issueRepository.findAll();
    }

    public Issue getById(final Long id){
        log.info("Issue with id: " + id + " requested");
        return issueRepository.findById(id).orElseThrow();
    }

    public Issue update(final Issue issue){
        log.info("Issue entitled: " + issue.getTitle() + " update");
        final Issue issueInDatabase = getById(issue.getId());
        issueInDatabase.setTitle(issue.getTitle());
        issueInDatabase.setDescription(issue.getDescription());
        return issueRepository.save(issueInDatabase);
    }

    public void moveToColumn(final Issue issue, final Long oldColumnId, final Long newColumnId){
        Column oldColumn = columnRepository.findById(oldColumnId).orElseThrow();
        Column newColumn = columnRepository.findById(newColumnId).orElseThrow();
        oldColumn.getIssues().remove(issue);
        columnRepository.save(oldColumn);
        newColumn.getIssues().add(issue);
        columnRepository.save(newColumn);
    }

    public void delete(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();
        Column column = columnRepository.getByIssuesContains(issue);
        column.getIssues().remove(issue);
        log.info("Issue with id: " + id + " removed from column: " + column.getName());
        issueRepository.deleteById(id);
        columnRepository.save(column);
    }
}
