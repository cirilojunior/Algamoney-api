package com.algaworks.algamoney.categoria;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long codigo;
    private String nome;

    public CategoriaDTO(CategoriaEntity entity) {
        this.codigo = entity.getCodigo();
        this.nome = entity.getNome();
    }

    public CategoriaEntity toEntity() {
        return CategoriaEntity
                .builder()
                .codigo(codigo)
                .nome(nome)
                .build();
    }
}
