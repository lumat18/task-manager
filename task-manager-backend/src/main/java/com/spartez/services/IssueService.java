package com.spartez.services;

import com.spartez.domain.Issue;
import com.spartez.repositories.IssueRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue create(final Issue issue){
        return issueRepository.save(issue);
    }

    public Collection<Issue> getAll(){
        return issueRepository.findAll();
    }

    public Issue getById(final Long id){
        return issueRepository.findById(id).orElseThrow();
    }

    public Issue update(final Issue issue){
        final Issue issueInDatabase = getById(issue.getId());
        issueInDatabase.setTitle(issue.getTitle());
        issueInDatabase.setDescription(issue.getDescription());
        return issueRepository.save(issueInDatabase);
    }

    public void delete(final Long id){
        issueRepository.deleteById(id);
    }
}
