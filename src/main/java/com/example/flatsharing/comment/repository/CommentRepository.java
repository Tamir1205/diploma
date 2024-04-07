package com.example.flatsharing.comment.repository;

import com.example.flatsharing.comment.domain.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByParentId(UUID parentId);
}
