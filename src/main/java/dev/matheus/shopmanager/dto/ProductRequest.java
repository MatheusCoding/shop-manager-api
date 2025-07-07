package dev.matheus.shopmanager.dto;

import jakarta.validation.constraints.*;

public record ProductRequest (
    @NotBlank(message = "Product name is required")
    String name,

    @Positive(message = "Product price must be greater than 0")
    double price,

    @PositiveOrZero(message = "Product quantity must be greater than or equal to 0")
    Integer stock

) {}
