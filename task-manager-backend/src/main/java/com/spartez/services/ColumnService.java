package com.spartez.services;

import com.spartez.domain.Column;
import com.spartez.repositories.ColumnRepository;
import com.spartez.repositories.IssueRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ColumnService {
    private final ColumnRepository columnRepository;
    private final IssueRepository issueRepository;

    public ColumnService(ColumnRepository columnRepository, IssueRepository issueRepository) {
        this.columnRepository = columnRepository;
        this.issueRepository = issueRepository;
    }

    public Column create(final Column column){
        return columnRepository.save(column);
    }

    public List<Column> getAll(){
        return columnRepository.findAll();
    }

    public Column getById(final Long id){
        return columnRepository.findById(id).orElseThrow();
    }

    public Column update(final Column column){
        Column columnInDatabase = getById(column.getId());
        columnInDatabase.setName(column.getName());
        columnInDatabase.setIssues(column.getIssues());
        issueRepository.saveAll(columnInDatabase.getIssues());
        return columnRepository.save(columnInDatabase);
    }

    public void delete(final Long id){
        columnRepository.deleteById(id);
    }
}
