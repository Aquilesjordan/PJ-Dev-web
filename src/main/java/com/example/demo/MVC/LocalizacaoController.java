package com.example.demo.MVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public List<Localizacao> getAllLocalizacoes() {
        return localizacaoService.getAllLocalizacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> getLocalizacaoById(@PathVariable Long id) {
        Optional<Localizacao> localizacao = localizacaoService.getLocalizacaoById(id);
        return localizacao.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Localizacao> saveLocalizacao(@RequestBody Localizacao localizacao) {
        Localizacao savedLocalizacao = localizacaoService.saveLocalizacao(localizacao);
        return new ResponseEntity<>(savedLocalizacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localizacao> updateLocalizacao(@PathVariable Long id, @RequestBody Localizacao localizacao) {
        Optional<Localizacao> existingLocalizacao = localizacaoService.getLocalizacaoById(id);

        if (existingLocalizacao.isPresent()) {
            Localizacao updatedLocalizacao = localizacaoService.updateLocalizacao(id, localizacao);
            return new ResponseEntity<>(updatedLocalizacao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalizacao(@PathVariable Long id) {
        localizacaoService.deleteLocalizacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
