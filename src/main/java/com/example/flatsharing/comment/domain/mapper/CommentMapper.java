package com.example.flatsharing.comment.domain.mapper;

import com.example.flatsharing.comment.domain.dto.CommentDTO;
import com.example.flatsharing.comment.domain.dto.CreateCommentDTO;
import com.example.flatsharing.comment.domain.dto.UpdateCommentDTO;
import com.example.flatsharing.comment.domain.model.Comment;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CommentMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
//    @Mapping(target = "createdAt", expression = "java(org.joda.time.DateTime.now())")
//    @Mapping(target = "updatedAt", expression = "java(org.joda.time.DateTime.now())")
    Comment toComment(CreateCommentDTO dto);

    CommentDTO toDTO(Comment model);

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", expression = "java(org.joda.time.DateTime.now())")
    void mapValues(UpdateCommentDTO updateCommentDTO, @MappingTarget Comment comment);
}
