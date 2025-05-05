package ru.paramonova.apiservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.paramonova.apiservice.dto.PostDto;
import ru.paramonova.apiservice.dto.ReportDto;
import ru.paramonova.apiservice.models.Comment;
import ru.paramonova.apiservice.models.Post;
import ru.paramonova.apiservice.models.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiServiceController {
    @Value("${data-service.base-url}")
    private String dataServiceBaseUrl;
    @Value("${data-service.port}")
    private String dataServicePort;
    @Value("${kafka.posts.topic}")
    private String postsTopic;
    @Value("${kafka.comments.topic}")
    private String commentsTopic;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        String url = String.format("%s:%s/posts", dataServiceBaseUrl, dataServicePort);
        ResponseEntity<List<Post>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Post>>() {
                }
        );
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        String url = String.format("%s:%s/posts/%s", dataServiceBaseUrl, dataServicePort, id);
        ResponseEntity<Post> response = restTemplate.getForEntity(url, Post.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> findAllComments() {
        String url = String.format("%s:%s/comments", dataServiceBaseUrl, dataServicePort);
        ResponseEntity<List<Comment>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Comment>>() {
                }
        );
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Long id) {
        String url = String.format("%s:%s/comments/%s", dataServiceBaseUrl, dataServicePort, id);
        ResponseEntity<Comment> response = restTemplate.getForEntity(url, Comment.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/topPosts")
    public ResponseEntity<List<Post>> findTopPostsByNumberComments() {
        String url = String.format("%s:%s/topPosts", dataServiceBaseUrl, dataServicePort);
        ResponseEntity<List<Post>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Post>>() {
                }
        );
        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/topUser")
    public ResponseEntity<User> findTopUserByNumberPosts() {
        String url = String.format("%s:%s/topUser", dataServiceBaseUrl, dataServicePort);
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/numberPostsAndComments")
    public ResponseEntity<List<ReportDto>> findPostAndCommentNumbersByDate() {
        String url = String.format("%s:%s/numberPostsAndComments", dataServiceBaseUrl, dataServicePort);
        ResponseEntity<List<ReportDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReportDto>>() {
                }
        );
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody PostDto postDto) {
        try {
            String postDtoJson = objectMapper.writeValueAsString(postDto);
            kafkaTemplate.send(postsTopic, postDtoJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}
