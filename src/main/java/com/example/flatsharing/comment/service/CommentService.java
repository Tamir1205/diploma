package com.example.flatsharing.comment.service;

import com.example.flatsharing.comment.domain.exception.CommentNotFoundException;
import com.example.flatsharing.comment.domain.dto.CreateCommentDTO;
import com.example.flatsharing.comment.domain.dto.UpdateCommentDTO;
import com.example.flatsharing.comment.domain.mapper.CommentMapper;
import com.example.flatsharing.comment.domain.model.Comment;
import com.example.flatsharing.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private  CommentMapper commentMapper;
    @Autowired
    private  CommentRepository commentRepository;

    public Comment create(CreateCommentDTO dto) {
        Comment comment = commentMapper.toComment(dto);
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> findByParentId(UUID parentId) {
        return commentRepository.findAllByParentId(parentId);
    }

    public Comment findById(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id " + id + " not found"));
    }

    public Comment update(String id, UpdateCommentDTO dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id " + id + "not found"));
        commentMapper.mapValues(dto, comment);
        commentRepository.save(comment);
        return comment;
    }

    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
