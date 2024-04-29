package com.example.flatsharing.comment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String id;
    private String authorId;
    private String advertisementId;
    private String content;
    private String parentId;
}
