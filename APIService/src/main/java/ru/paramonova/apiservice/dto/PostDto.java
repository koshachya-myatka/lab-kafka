package ru.paramonova.apiservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
    @JsonProperty("author_id")
    private Long authorId;
    private String content;
}
