package br.com.bandtec.continuada2.controller;

import br.com.bandtec.continuada2.controller.dto.LutadorDto;
import br.com.bandtec.continuada2.dominio.Lutador;
import br.com.bandtec.continuada2.repositorio.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @GetMapping
    public ResponseEntity getLutador(){
        List<Lutador> lutadores = repository.findAllByOrderByForcaGolpeDesc();
        if (lutadores.isEmpty()) {
            return status(204).build();
        } else {
            return status(200).body(lutadores);
        }
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getLutadorVivos(){
        Integer vivos =repository.countByVivo(true);

        if (vivos>0) {
            return status(200).body(vivos);
        } else {
            return status(204).build();
        }
    }
    @PostMapping
    public ResponseEntity postLutador(@RequestBody @Valid LutadorDto l){
        Lutador novoLutador=new Lutador(null, l.getNome(),l.getForcaGolpe(),100.0,0,true);
        repository.save(novoLutador);
        return status(201).build();
    }
}
