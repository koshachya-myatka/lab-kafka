package ru.paramonova.dataservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.paramonova.dataservice.dto.CommentDto;
import ru.paramonova.dataservice.dto.PostDto;
import ru.paramonova.dataservice.models.Comment;
import ru.paramonova.dataservice.models.Post;
import ru.paramonova.dataservice.models.User;
import ru.paramonova.dataservice.repositories.CommentRepository;
import ru.paramonova.dataservice.repositories.PostRepository;
import ru.paramonova.dataservice.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public Optional<Comment> addComment(CommentDto commentDto) {
        Optional<User> user = userRepository.findById(commentDto.getUserId());
        Optional<Post> post = postRepository.findById(commentDto.getPostId());
        if (user.isEmpty() || post.isEmpty()) return Optional.empty();
        Comment comment = Comment.builder().user(user.get()).post(post.get()).content(commentDto.getContent()).dateCreated(LocalDateTime.now()).build();
        return Optional.of(commentRepository.save(comment));
    }
}
