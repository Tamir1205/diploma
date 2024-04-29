package com.example.flatsharing.comment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedCommentDTO {
    private List<CommentDTO> content;
    private Long totalItems;
    private int totalPages;
    private int pageNumber;
}
