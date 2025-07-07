package com.example.githubsearcher.controller;

import lombok.Data;

@Data
public class SearchRequest {
    private String query;
    private String language;
    private String sort;
}
