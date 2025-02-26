package com.embarkx.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private List<CategoryDTO> categories;

    public void setPageDetails(Integer pageNumber, Integer pageSize, Long totalElements, Integer totalPages, boolean lastPage) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
        setTotalElements(totalElements);
        setTotalPages(totalPages);
        setLastPage(lastPage);
    }

    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;
}
