package com.example.demo.MVC;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultaService {

    private final MultaRepository multaRepository;

    public MultaService(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    public List<Multa> listarTodas() {
        return multaRepository.findAll();
    }

    public Optional<Multa> buscarPorId(Long id) {
        return multaRepository.findById(id);
    }

    public Multa salvar(Multa multa) {
        return multaRepository.save(multa);
    }

    public void deletarPorId(Long id) {
        multaRepository.deleteById(id);
    }
}

