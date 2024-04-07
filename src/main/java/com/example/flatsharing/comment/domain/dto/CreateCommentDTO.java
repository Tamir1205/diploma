package com.example.flatsharing.comment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentDTO {
    private String authorId;
    private String content;
    private String parentId;
}
