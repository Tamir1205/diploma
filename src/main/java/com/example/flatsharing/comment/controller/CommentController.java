package com.example.flatsharing.comment.controller;

import com.example.flatsharing.comment.domain.dto.CommentDTO;
import com.example.flatsharing.comment.domain.dto.CreateCommentDTO;
import com.example.flatsharing.comment.domain.dto.UpdateCommentDTO;
import com.example.flatsharing.comment.domain.mapper.CommentMapper;
import com.example.flatsharing.comment.domain.model.Comment;
import com.example.flatsharing.comment.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
}
