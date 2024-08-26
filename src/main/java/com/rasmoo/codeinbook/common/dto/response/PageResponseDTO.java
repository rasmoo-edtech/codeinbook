package com.rasmoo.codeinbook.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class PageResponseDTO<T> {

    private List<T> content;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;

}
