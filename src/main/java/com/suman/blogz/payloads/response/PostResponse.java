package com.suman.blogz.payloads.response;

import com.suman.blogz.payloads.dto.PostDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PostResponse {
    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private Boolean lastPage;
}
