package com.pragma.powerup.stockservice.domains.model;

import java.util.List;

public class PagedList<T> {
    private List<T> content;         // Contenido de la página (elementos de tipo genérico)
    private int pageNumber;          // Número de la página actual
    private int pageSize;            // Tamaño de la página
    private long totalElements;      // Número total de elementos
    private int totalPages;          // Número total de páginas
    private boolean isFirst;         // Es la primera página?
    private boolean isLast;          // Es la última página?

    public PagedList(List<T> content, int pageNumber, int pageSize, long totalElements) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
        this.isFirst = pageNumber == 1;
        this.isLast = pageNumber == totalPages;
    }

    public static <T> PagedList<T> of(List<T> content, int pageNumber, int pageSize, long totalElements) {
        return new PagedList<>(content, pageNumber, pageSize, totalElements);
    }

    // Getters y setters
    public List<T> getContent() { return content; }
    public void setContent(List<T> content) { this.content = content; }
    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }
    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    public boolean isFirst() { return isFirst; }
    public void setFirst(boolean first) { isFirst = first; }
    public boolean isLast() { return isLast; }
    public void setLast(boolean last) { isLast = last; }
}
