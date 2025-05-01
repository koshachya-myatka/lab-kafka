package ru.paramonova.apiservice.models;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    private Long id;
    private Post post;
    private User user;
    private String content;
    private LocalDateTime dateCreated;
}
