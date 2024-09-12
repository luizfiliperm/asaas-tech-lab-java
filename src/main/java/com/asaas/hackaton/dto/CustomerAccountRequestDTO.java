package com.asaas.hackaton.dto;

import java.util.Objects;

public record CustomerAccountRequestDTO(
        String name,
        String cpfCnpj
) {
    public CustomerAccountRequestDTO {
        Objects.requireNonNull(name, "É necessário informar o nome do cliente");
        Objects.requireNonNull(cpfCnpj, "É necessário informar o documento do cliente");
    }
}
