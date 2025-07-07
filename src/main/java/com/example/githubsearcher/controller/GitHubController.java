package com.example.githubsearcher.controller;

import com.example.githubsearcher.model.RepositoryEntity;
import com.example.githubsearcher.repository.RepositoryEntityRepository;
import com.example.githubsearcher.service.GitHubService;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubService gitHubService;
    private final RepositoryEntityRepository repo;

    // POST /api/github/search
    @PostMapping("/search")
    public Response search(@RequestBody SearchRequest request) {
        List<RepositoryEntity> repositories =
                gitHubService.searchAndSave(request.getQuery(), request.getLanguage(), request.getSort());
        return new Response("Repositories fetched and saved successfully", repositories);
    }

    // GET /api/github/repositories?language=Java&minStars=100&sort=stars
    @GetMapping("/repositories")
    public List<RepositoryEntity> getRepos(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(defaultValue = "stars") String sort
    ) {
        return repo.findAll().stream()
                .filter(r -> language == null || r.getLanguage().equalsIgnoreCase(language))
                .filter(r -> minStars == null || r.getStars() >= minStars)
                .sorted(getSortComparator(sort))
                .collect(Collectors.toList());
    }

    private Comparator<RepositoryEntity> getSortComparator(String sort) {
        return switch (sort) {
            case "forks" -> Comparator.comparing(RepositoryEntity::getForks).reversed();
            case "updated" -> Comparator.comparing(RepositoryEntity::getLastUpdated).reversed();
            default -> Comparator.comparing(RepositoryEntity::getStars).reversed();
        };
    }
}
