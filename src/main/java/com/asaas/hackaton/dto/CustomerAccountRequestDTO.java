package com.asaas.hackaton.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public record CustomerAccountRequestDTO(
        @Schema(example = "John")
        String name,
        @Schema(example = "000.000.000-00")
        String cpfCnpj
) {
    public CustomerAccountRequestDTO {
        Objects.requireNonNull(name, "É necessário informar o nome do cliente");
        Objects.requireNonNull(cpfCnpj, "É necessário informar o documento do cliente");
    }
}
