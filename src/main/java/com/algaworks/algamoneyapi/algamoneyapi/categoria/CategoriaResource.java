package com.algaworks.algamoneyapi.algamoneyapi.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<CategoriaDTO> listarTodos() {
        List<CategoriaDTO> categorias = new ArrayList<>();
        repository.findAll().forEach(c -> categorias.add(new CategoriaDTO(c)));
        return categorias;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@RequestBody CategoriaDTO categoria, HttpServletResponse response) {
        CategoriaEntity entity = repository.save(categoria.toEntity());

        URI novaUri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{codigo}")
                .buildAndExpand(entity.getCodigo())
                .toUri();
        response.setHeader("Location", novaUri.toASCIIString());

        return ResponseEntity
                .created(novaUri)
                .body(new CategoriaDTO(entity));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscaPeloCodigo(@PathVariable Long codigo) {
        CategoriaEntity entity = repository.findOne(codigo);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new CategoriaDTO(entity));
    }
}
