package com.gerente.gerente.domain.product;

public enum ProductCategory {
    ELETRONIC("Eletrônico"),
    CASE("Capinha"),
    PELLICLE("Película");

    private String category;

    ProductCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
