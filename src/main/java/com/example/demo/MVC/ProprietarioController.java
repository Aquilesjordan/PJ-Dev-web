package com.example.demo.MVC;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @GetMapping
    public List<Proprietario> listarTodos() {
        return proprietarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscarPorId(@PathVariable Long id) {
        Optional<Proprietario> proprietario = proprietarioService.buscarPorId(id);
        return proprietario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proprietario criar(@RequestBody Proprietario proprietario) {
        return proprietarioService.salvar(proprietario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long id, @RequestBody Proprietario proprietario) {
        Optional<Proprietario> existente = proprietarioService.buscarPorId(id);
        if (existente.isPresent()) {
            proprietario.setId(id);
            return ResponseEntity.ok(proprietarioService.salvar(proprietario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (proprietarioService.buscarPorId(id).isPresent()) {
            proprietarioService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
