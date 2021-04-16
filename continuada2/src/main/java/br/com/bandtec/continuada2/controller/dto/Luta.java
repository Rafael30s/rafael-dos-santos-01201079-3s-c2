package br.com.bandtec.continuada2.controller.dto;

import javax.validation.constraints.Positive;

public class Luta {
    @Positive
    private Integer idLutadorBate;
    @Positive
    private Integer idLutadorApanha;

    public Integer getIdLutadorBate() {
        return idLutadorBate;
    }

    public void setIdLutadorBate(Integer idLutadorBate) {
        this.idLutadorBate = idLutadorBate;
    }

    public Integer getIdLutadorApanha() {
        return idLutadorApanha;
    }

    public void setIdLutadorApanha(Integer idLutadorApanha) {
        this.idLutadorApanha = idLutadorApanha;
    }
}
