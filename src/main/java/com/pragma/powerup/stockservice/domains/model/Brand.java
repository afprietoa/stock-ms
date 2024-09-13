package com.pragma.powerup.stockservice.domains.model;

public class Brand {
    private Long idBrand;
    private String name;
    private String description;

    public Brand() {
    }

    public Brand( String name, String description) {

        this.name = name;
        this.description = description;
    }

    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
