package br.com.bandtec.continuada2.controller.dto;
import javax.validation.constraints.*;


public class LutadorDto {

    @NotBlank
    @Size(min=3, max=12)
    private String nome;
    @Positive
    private Double forcaGolpe;

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
}
