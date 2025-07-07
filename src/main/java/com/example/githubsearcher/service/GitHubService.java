package com.example.githubsearcher.service;

import com.example.githubsearcher.model.RepositoryEntity;
import com.example.githubsearcher.repository.RepositoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubService {

    private final RepositoryEntityRepository repo;

    public List<RepositoryEntity> searchAndSave(String query, String language, String sort) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://api.github.com/search/repositories?q=%s+language:%s&sort=%s", query, language, sort);

        // Make API call
        JSONObject response = new JSONObject(restTemplate.getForObject(url, String.class));
        JSONArray items = response.getJSONArray("items");

        List<RepositoryEntity> savedRepos = new ArrayList<>();

        // Parse and save each repo
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            RepositoryEntity entity = new RepositoryEntity(
                    item.getLong("id"),
                    item.getString("name"),
                    item.optString("description", ""),
                    item.getJSONObject("owner").getString("login"),
                    item.optString("language", ""),
                    item.getInt("stargazers_count"),
                    item.getInt("forks_count"),
                    item.getString("updated_at")
            );

            savedRepos.add(repo.save(entity)); // Spring Data JPA handles insert/update
        }

        return savedRepos;
    }
}
