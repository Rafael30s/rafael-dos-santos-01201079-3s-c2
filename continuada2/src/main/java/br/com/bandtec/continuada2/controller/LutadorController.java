package br.com.bandtec.continuada2.controller;

import br.com.bandtec.continuada2.controller.dto.Luta;
import br.com.bandtec.continuada2.controller.dto.LutadorDto;
import br.com.bandtec.continuada2.dominio.Lutador;
import br.com.bandtec.continuada2.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @GetMapping
    public ResponseEntity getLutador() {
        List<Lutador> lutadores = repository.findAllByOrderByForcaGolpeDesc();
        if (lutadores.isEmpty()) {
            return status(204).build();
        } else {
            return status(200).body(lutadores);
        }
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getLutadorVivos() {
        Integer vivos = repository.countByVivo(true);

        if (vivos > 0) {
            return status(200).body(vivos);
        } else {
            return status(204).build();
        }
    }

    @GetMapping("/mortos")
    public ResponseEntity getLutadorMortos() {
        Integer mortos = repository.countByVivo(false);

        if (mortos > 0) {
            return status(200).body(mortos);
        } else {
            return status(204).build();
        }
    }

    @PostMapping
    public ResponseEntity postLutador(@RequestBody @Valid LutadorDto l) {
        Lutador novoLutador = new Lutador(null, l.getNome(), l.getForcaGolpe(), 100.0, 0, true);
        repository.save(novoLutador);
        return status(201).build();
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity postLutadorConcentrar(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            Lutador lutador = repository.findById(id).get();
            Integer conce = (lutador.getConcentracoesRealizadas());
            conce++;
            lutador.setConcentracoesRealizadas(conce);
            lutador.setVida(lutador.getVida() + (lutador.getVida() * 1.5));
            repository.save(lutador);
            return ok().build();
        } else {
            return status(204).build();
        }
    }

    @PostMapping("/golpe")
    public ResponseEntity postLutador(@RequestBody @Valid Luta l) {
        if(repository.existsById(l.getIdLutadorApanha())&& repository.existsById(l.getIdLutadorBate())){
            Lutador lutadorApanha = repository.findById(l.getIdLutadorApanha()).get();
            Lutador lutadorBate = repository.findById(l.getIdLutadorBate()).get();
            if(lutadorApanha.getVivo()&& lutadorBate.getVivo()){

                Double novaVida = lutadorApanha.getVida() - lutadorBate.getForcaGolpe();
                if (novaVida <= 0) {
                    novaVida = 0.0;
                }
                lutadorApanha.setVida(novaVida);
                repository.save(lutadorApanha);
                List<Lutador> lista = new ArrayList<>();
                lutadorApanha.verificarVida();
                lista.add(lutadorBate);
                lista.add(lutadorApanha);

                return status(201).body(lista);
            }
            else{
                return status(400).body("Ambos os lutadores devem estar vivos!");
            }

        }
        else {
            return notFound().build();
        }
    }

}
