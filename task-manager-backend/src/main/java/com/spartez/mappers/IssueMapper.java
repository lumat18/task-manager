package com.spartez.mappers;

import com.spartez.domain.Issue;
import com.spartez.model.IssueDto;

public interface IssueMapper {
    IssueDto mapToIssueDto(final Issue issue);
    Issue mapToIssue(final IssueDto issueDto);
}
