package com.example.flatsharing.comment.domain.mapper;

import com.example.flatsharing.comment.domain.dto.CommentDTO;
import com.example.flatsharing.comment.domain.dto.CreateCommentDTO;
import com.example.flatsharing.comment.domain.dto.UpdateCommentDTO;
import com.example.flatsharing.comment.domain.model.Comment;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommentMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    Comment toComment(CreateCommentDTO dto);

    CommentDTO toDTO(Comment model);

    List<CommentDTO> toDTO(List<Comment> models);

    @Mapping(target = "id", ignore = true)
    void mapValues(UpdateCommentDTO updateCommentDTO, @MappingTarget Comment comment);
}
