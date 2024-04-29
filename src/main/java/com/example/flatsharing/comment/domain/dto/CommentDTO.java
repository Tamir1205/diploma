package com.example.flatsharing.comment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String id;
    private String authorId;
    private String content;
    private String parentId;
    private String advertisementId;
}
