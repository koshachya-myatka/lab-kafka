package ru.paramonova.apiservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.paramonova.apiservice.models.Post;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiServiceController {
    private final RestTemplate restTemplate;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        //todo
        String url = "";
//        restTemplate.exchange(url, HttpMethod.GET, null, ParameterizedTypeReference<List<Post>>);
        return ResponseEntity.ok().build();
    }
}
