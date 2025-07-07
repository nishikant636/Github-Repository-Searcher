package com.example.githubsearcher.controller;

import com.example.githubsearcher.model.RepositoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Response {
    private String message;
    private List<RepositoryEntity> repositories;
}
