package com.spartez.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueDto {
    private Long id;
    private String title;
    private String description;
    private Long columnIndex;

    public IssueDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
