package com.spartez.bootstrap;

import com.spartez.domain.Column;
import com.spartez.domain.Issue;
import com.spartez.repositories.ColumnRepository;
import com.spartez.repositories.IssueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final IssueRepository issueRepository;
    private final ColumnRepository columnRepository;

    public DatabaseInit(IssueRepository issueRepository, ColumnRepository columnRepository) {
        this.issueRepository = issueRepository;
        this.columnRepository = columnRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Issue> issues = List.of(
                new Issue("Drink coffee", "Double espresso to start"),
                new Issue("Walk the dog", "Go into the woods"),
                new Issue("Code along", "All day all night"),
                new Issue("Go to the gym", "Train hard"),
                new Issue("Eat lunch", "I'm super hungry"),
                new Issue("Grab a beer", "Drunk as fuck"),
                new Issue("Play guitar", "Practice makes perfect"),
                new Issue("Go to the office", "Air condition here")
        );

        List<Column> columns = List.of(
                new Column("To do", Set.of(
                        issues.get(0),
                        issues.get(1),
                        issues.get(2)
                )),
                new Column("In progress", Set.of(
                        issues.get(3),
                        issues.get(4),
                        issues.get(5),
                        issues.get(6)
                )),
                new Column("Done", Set.of(
                        issues.get(7)
                ))
        );

        issueRepository.saveAll(issues);
        columnRepository.saveAll(columns);

    }
}
