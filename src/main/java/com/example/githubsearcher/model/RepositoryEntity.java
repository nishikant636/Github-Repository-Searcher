package com.example.githubsearcher.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repositories")
public class RepositoryEntity {

    @Id
    private Long id; // GitHub Repo ID

    private String name;
    private String description;
    private String owner;
    private String language;
    private int stars;
    private int forks;

    @Column(name = "last_updated")
    private String lastUpdated;
}
