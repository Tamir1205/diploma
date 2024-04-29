package com.example.flatsharing.comment.service;

import com.example.flatsharing.advertisement.domain.model.Advertisement;
import com.example.flatsharing.advertisement.service.AdvertisementService;
import com.example.flatsharing.comment.domain.dto.CreateCommentDTO;
import com.example.flatsharing.comment.domain.dto.UpdateCommentDTO;
import com.example.flatsharing.comment.domain.mapper.CommentMapper;
import com.example.flatsharing.comment.domain.model.Comment;
import com.example.flatsharing.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Page<Comment> paginate(Pageable pageable,
                                  String advertisementId) {
        Query query = new Query();
        if (advertisementId != null) {
            query.addCriteria(Criteria.where("advertisementId").is(advertisementId));
        }
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        return PageableExecutionUtils.getPage(
                comments,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Comment.class));
    }

    public Comment create(CreateCommentDTO dto) {
        Advertisement advertisement = advertisementService.findById(dto.getAdvertisementId());
        Comment comment = commentMapper.toComment(dto);
        commentRepository.save(comment);
        if (advertisement.getCommentIds() != null && !advertisement.getCommentIds().isEmpty()) {
            advertisement.getCommentIds().add(comment.getId());
        } else {
            List<String> commentIds = new ArrayList<>();
            commentIds.add(comment.getId());
            advertisement.setCommentIds(commentIds);
        }
        advertisementService.save(advertisement);
        return comment;
    }

    public List<Comment> findByParentId(String parentId) {
        return commentRepository.findAllByParentId(parentId);
    }

    public Comment findById(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment with id " + id + " not found"));
    }

    public Comment update(String id, UpdateCommentDTO dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment with id " + id + "not found"));
        commentMapper.mapValues(dto, comment);
        commentRepository.save(comment);
        return comment;
    }

    public void deleteById(String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment with id " + id + "not found"));
        commentRepository.deleteById(id);
    }
}
