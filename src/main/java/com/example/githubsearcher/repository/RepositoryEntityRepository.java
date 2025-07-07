package com.example.githubsearcher.repository;

import com.example.githubsearcher.model.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEntityRepository extends JpaRepository<RepositoryEntity, Long> {
}
