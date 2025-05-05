package ru.paramonova.apiservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDto {
    private LocalDateTime date;
    @JsonProperty("post_count")
    private int postCount;
    @JsonProperty("comment_count")
    private int commentCount;
}
