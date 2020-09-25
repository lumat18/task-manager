package com.spartez.services;

import com.spartez.domain.Column;
import com.spartez.domain.Issue;
import com.spartez.repositories.ColumnRepository;
import com.spartez.repositories.IssueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final IssueRepository issueRepository;

    public ColumnService(ColumnRepository columnRepository, IssueRepository issueRepository) {
        this.columnRepository = columnRepository;
        this.issueRepository = issueRepository;
    }

    public Column create(final Column column) {
        log.info("Column entitled: " + column.getName() + " created");
        return columnRepository.save(column);
    }

    public List<Column> getAll() {
        log.info("All columns requested");
        return columnRepository.findAll();
    }

    public Column getById(final Long id) {
        log.info("Column with id: " + id + " requested");
        return columnRepository.findById(id).orElseThrow();
    }

    public Column update(final Column column) {
        log.info("Column entitled: " + column.getName() + " update");
        Column columnInDatabase = getById(column.getId());
        columnInDatabase.setName(column.getName());
        columnInDatabase.setIssues(column.getIssues());
        issueRepository.saveAll(columnInDatabase.getIssues());
        return columnRepository.save(columnInDatabase);
    }

    public void delete(final Long id) {
        log.info("Column with id: " + id + " deleted");
        columnRepository.deleteById(id);
    }
}
