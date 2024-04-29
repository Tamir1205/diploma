package com.example.flatsharing.comment.controller;

import com.example.flatsharing.comment.domain.dto.CommentDTO;
import com.example.flatsharing.comment.domain.dto.CreateCommentDTO;
import com.example.flatsharing.comment.domain.dto.PaginatedCommentDTO;
import com.example.flatsharing.comment.domain.dto.UpdateCommentDTO;
import com.example.flatsharing.comment.domain.mapper.CommentMapper;
import com.example.flatsharing.comment.domain.model.Comment;
import com.example.flatsharing.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    @PostMapping
    public CommentDTO createComment(@Valid @RequestBody CreateCommentDTO dto) {
        Comment comment = commentService.create(dto);
        return commentMapper.toDTO(comment);
    }

    @PutMapping("/{id}")
    public CommentDTO updateComment(@RequestBody UpdateCommentDTO dto, @PathVariable String id) {
        Comment comment = commentService.update(id, dto);
        return commentMapper.toDTO(comment);
    }

    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable String id) {
        return commentMapper.toDTO(commentService.findById(id));
    }

    @GetMapping("/parent/{parentId}")
    public List<CommentDTO> getCommentsByParentId(@PathVariable String parentId) {
        return commentMapper.toDTO(commentService.findByParentId(parentId));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        commentService.deleteById(id);
    }

    @GetMapping
    @PageableAsQueryParam
    public PaginatedCommentDTO paginate(@Parameter(hidden = true) Pageable pageable,
                                        @RequestParam(required = false) String advertisementId) {
        Page<Comment> page = commentService.paginate(pageable, advertisementId);
        return new PaginatedCommentDTO(page.getContent().stream().map(commentMapper::toDTO).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages(), page.getNumber());
    }
}
