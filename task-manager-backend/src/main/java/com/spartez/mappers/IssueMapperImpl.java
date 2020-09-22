package com.spartez.mappers;

import com.spartez.domain.Issue;
import com.spartez.model.IssueDto;
import org.springframework.stereotype.Component;

@Component
public class IssueMapperImpl implements IssueMapper{

    @Override
    public IssueDto mapToIssueDto(Issue issue) {
        return new IssueDto(issue.getId(), issue.getTitle(), issue.getDescription());
    }

    @Override
    public Issue mapToIssue(IssueDto issueDto) {
        return new Issue(issueDto.getTitle(), issueDto.getDescription());
    }
}
