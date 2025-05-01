package ru.paramonova.dataservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.paramonova.dataservice.dto.CommentDto;
import ru.paramonova.dataservice.dto.PostDto;
import ru.paramonova.dataservice.models.Comment;
import ru.paramonova.dataservice.models.Post;
import ru.paramonova.dataservice.repositories.CommentRepository;
import ru.paramonova.dataservice.repositories.PostRepository;
import ru.paramonova.dataservice.repositories.UserRepository;

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
        if (userRepository.findById(commentDto.getUserId()).isEmpty() || postRepository.findById(commentDto.getPostId()).isEmpty())
            return Optional.empty();
        Comment comment = objectMapper.convertValue(commentDto, Comment.class);
        return Optional.of(commentRepository.save(comment));
    }
}
