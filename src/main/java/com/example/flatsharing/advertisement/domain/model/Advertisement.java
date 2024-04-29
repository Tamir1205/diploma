package com.example.flatsharing.advertisement.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document("advertisements")
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {
    @Id
    private String id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private List<byte[]> photos;
    @NotEmpty
    private City city;
    private String address;
    @NotNull
    private SubjectType type;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer numberOfRooms;
    private List<String> conditions;
    private Boolean isPromoted;
    private List<String> commentIds;
    private String authorId;
}
