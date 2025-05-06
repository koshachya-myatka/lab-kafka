package ru.paramonova.dataservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDto {
    private Date date;
    @JsonProperty("post_count")
    private Long postCount;
    @JsonProperty("comment_count")
    private Long commentCount;
}
