package com.example.graphql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Book{
    private Integer id;
    private String name;
    private Integer pageCount;
}
