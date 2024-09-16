package com.pragma.powerup.stockservice.adapters.driving.http.dto.request;

import com.pragma.powerup.stockservice.domains.model.PagingParams;

public class ProductPagingRequestDto extends PagingParams {
    private String orderBy;

    public ProductPagingRequestDto(int pageNumber, int pageSize, String orderBy, boolean isAscending) {
        super(pageNumber, pageSize, isAscending);
        this.orderBy = orderBy != null ? orderBy : "name"; // Default ordering by product name
    }
    public ProductPagingRequestDto(){}

    public String getOrderBy() { return orderBy; }
    public void setOrderBy(String orderBy) { this.orderBy = orderBy; }
}