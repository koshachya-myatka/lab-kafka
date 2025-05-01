package ru.paramonova.apiservice.models;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    private Long id;
    private User author;
    private String content;
    private LocalDateTime dateCreated;
}
