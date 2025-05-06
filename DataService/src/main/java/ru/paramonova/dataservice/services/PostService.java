package ru.paramonova.dataservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.paramonova.dataservice.dto.PostDto;
import ru.paramonova.dataservice.dto.ReportDto;
import ru.paramonova.dataservice.models.Post;
import ru.paramonova.dataservice.models.User;
import ru.paramonova.dataservice.repositories.PostRepository;
import ru.paramonova.dataservice.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public Optional<Post> addPost(PostDto postDto) {
        Optional<User> author = userRepository.findById(postDto.getAuthorId());
        if (author.isEmpty()) return Optional.empty();
        Post post = Post.builder().author(author.get()).content(postDto.getContent()).dateCreated(LocalDateTime.now()).build();
        return Optional.of(postRepository.save(post));
    }

    public List<Post> findTopPostsByNumberComments() {
        return postRepository.findTopPostsByNumberComments();
    }

    public Optional<User> findTopUserByNumberPosts() {
        return userRepository.findTopUserByNumberPosts();
    }

    public List<ReportDto> findPostAndCommentCountsByDate() {
        return postRepository.findPostAndCommentCountsByDate();
    }
}
