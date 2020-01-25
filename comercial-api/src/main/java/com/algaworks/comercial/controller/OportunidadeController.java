package com.algaworks.comercial.controller;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

    @Autowired
    private OportunidadeRepository oportunidades;

    @GetMapping
    public List<Oportunidade> listar() {
        return oportunidades.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
        Optional<Oportunidade> oportunidade = oportunidades.findById(id);
        if (oportunidade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oportunidade.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Oportunidade adicionar(@Valid @RequestBody Oportunidade oportunidade) {
        Optional<Oportunidade> oportunidadeExistente = oportunidades.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
        if (oportunidadeExistente.isPresent()) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um prospecto com esta descrição.");
        }

        return oportunidades.save(oportunidade);
    }
}
