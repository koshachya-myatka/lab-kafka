package ru.paramonova.dataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDto {
    @JsonProperty("post_id")
    private Long postId;
    @JsonProperty("user_id")
    private Long userId;
    private String content;
    @JsonProperty("date_created")
    private LocalDateTime dateCreated;
}
