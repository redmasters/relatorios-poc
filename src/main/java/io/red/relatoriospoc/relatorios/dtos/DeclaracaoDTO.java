package io.red.relatoriospoc.relatorios.dtos;

public record DeclaracaoDTO(
        Long id,
        String nome,
        String role,
        String lane,
        String dataAdmissao,
        String dataDemissao,
        String dataEmissao,
        String criadoPor

) {
}
