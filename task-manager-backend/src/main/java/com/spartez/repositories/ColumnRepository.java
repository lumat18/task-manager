package com.spartez.repositories;

import com.spartez.domain.Column;
import com.spartez.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ColumnRepository extends JpaRepository<Column, Long> {
    Column getByIssuesContains(final Issue issue);
}
