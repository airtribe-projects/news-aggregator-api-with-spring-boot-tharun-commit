package com.example.NewsAggregatorAPIwithSpringBoot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    @Value("${news.api.key}")
    private String apiKey;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Object> fetchNews(String category, String country) {
        WebClient webClient = webClientBuilder.build();

        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("newsapi.org")
                        .path("/v2/top-headlines")
                        .queryParam("apiKey", apiKey)
                        .queryParam("category", category)
                        .queryParam("country", country)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        return (List<Object>) response.get("articles");
    }
}