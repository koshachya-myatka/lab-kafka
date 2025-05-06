package ru.paramonova.dataservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.paramonova.dataservice.dto.ReportDto;
import ru.paramonova.dataservice.models.Comment;
import ru.paramonova.dataservice.models.Post;
import ru.paramonova.dataservice.models.User;
import ru.paramonova.dataservice.services.CommentService;
import ru.paramonova.dataservice.services.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DataServiceController {
    private final PostService postService;
    private final CommentService commentService;

    // посты
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        return postOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // комментарии
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> findAllComments() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        return commentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // отчеты
    @GetMapping("/topPosts")
    public ResponseEntity<List<Post>> findTopPostsByNumberComments() {
        return ResponseEntity.ok(postService.findTopPostsByNumberComments());
    }

    @GetMapping("/topUser")
    public ResponseEntity<User> findTopUserByNumberPosts() {
        return postService.findTopUserByNumberPosts().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/numberPostsAndComments")
    public ResponseEntity<List<ReportDto>> findPostAndCommentNumbersByDate() {
        return ResponseEntity.ok(postService.findPostAndCommentCountsByDate());
    }
}
