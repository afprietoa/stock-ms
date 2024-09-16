package com.pragma.powerup.stockservice.domains.model;

public abstract class PagingParams {
    private int pageNumber = 1;
    private int pageSize = 10;
    private String orderBy = "name";  // Campo por defecto para ordenar
    private boolean isAscending = true;

    public PagingParams() {
    }

    public PagingParams(int pageNumber, int pageSize, boolean isAscending) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.isAscending = isAscending;
    }

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }
    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    public String getOrderBy() { return orderBy; }
    public void setOrderBy(String orderBy) { this.orderBy = orderBy; }
    public boolean isAscending() { return isAscending; }
    public void setAscending(boolean isAscending) { this.isAscending = isAscending; }
}