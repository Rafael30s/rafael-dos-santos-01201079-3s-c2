package br.com.bandtec.continuada2.repositorio;

import br.com.bandtec.continuada2.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {

    List<Lutador> findAllByOrderByForcaGolpeDesc();
    Integer countByVivo(Boolean vivo);
}
