package com.spartez.mappers;

import com.spartez.domain.Column;
import com.spartez.domain.Issue;
import com.spartez.model.ColumnDto;
import com.spartez.model.IssueDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ColumnMapperImpl implements ColumnMapper{
    private final IssueMapper issueMapper;

    public ColumnMapperImpl(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    @Override
    public ColumnDto mapToColumnDto(Column column) {
        final Set<IssueDto> issueDtoList = column.getIssues().stream()
                .map(issueMapper::mapToIssueDto)
                .collect(Collectors.toSet());
        return new ColumnDto(column.getId(), column.getName(), issueDtoList);
    }

    @Override
    public Column mapToColumn(ColumnDto columnDto) {
        final Set<Issue> issueList = columnDto.getIssues().stream()
                .map(issueMapper::mapToIssue)
                .collect(Collectors.toSet());
        return new Column(columnDto.getName(), issueList);
    }
}
