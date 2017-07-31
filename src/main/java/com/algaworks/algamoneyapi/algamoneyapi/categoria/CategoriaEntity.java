package com.algaworks.algamoneyapi.algamoneyapi.categoria;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "codigo")
@AllArgsConstructor
@NoArgsConstructor
class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long codigo;

    String nome;

}
