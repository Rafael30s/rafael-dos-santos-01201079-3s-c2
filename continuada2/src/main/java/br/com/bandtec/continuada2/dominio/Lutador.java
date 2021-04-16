package br.com.bandtec.continuada2.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lutador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Double forcaGolpe;
    private Double vida;
    private Integer concentracoesRealizadas;
    private Boolean vivo;

    public Lutador(Integer id, String nome, Double forcaGolpe, Double vida, Integer concentracoesRealizadas, Boolean vivo) {
        this.id = id;
        this.nome = nome;
        this.forcaGolpe = forcaGolpe;
        this.vida = vida;
        this.concentracoesRealizadas = concentracoesRealizadas;
        this.vivo = vivo;
    }

    public Lutador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getForcaGolpe() {
        return forcaGolpe;
    }

    public void setForcaGolpe(Double forcaGolpe) {
        this.forcaGolpe = forcaGolpe;
    }

    public Double getVida() {
        return vida;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public Integer getConcentracoesRealizadas() {
        return concentracoesRealizadas;
    }

    public void setConcentracoesRealizadas(Integer concentracoesRealizadas) {
        this.concentracoesRealizadas = concentracoesRealizadas;
    }

    public Boolean getVivo() {
        return vivo;
    }

    public void setVivo(Boolean vivo) {
        this.vivo = vivo;
    }

    public void verificarVida(){
        if(this.vida<=0){
            this.vivo=false;
        }
    }
}
