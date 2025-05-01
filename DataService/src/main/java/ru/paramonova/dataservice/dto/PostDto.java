package ru.paramonova.dataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
    @JsonProperty("author_id")
    private Long authorId;
    private String content;
    @JsonProperty("date_created")
    private LocalDateTime dateCreated;
}
