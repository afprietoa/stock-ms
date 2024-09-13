package com.pragma.powerup.stockservice.adapters.driving.http.dto.response;

import com.pragma.powerup.stockservice.domains.model.PagingParams;

public class BrandPagingRequestDto extends PagingParams {
    private String name;  // Puedes agregar otros filtros si es necesario.

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
