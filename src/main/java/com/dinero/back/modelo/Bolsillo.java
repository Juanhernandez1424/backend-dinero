package com.dinero.back.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tabla_bolsillos")
public class Bolsillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_bolsillo")
    private int idBolsillo;
    private int idUsuario;
    private String descripcionBolsillo;
    private int valorBolsillo;

    public Bolsillo() {
    }

    public Bolsillo(int idBolsillo, int IdUsuario, String descripcionBosillo, int valorBolsillo) {
        this.idBolsillo = idBolsillo;
        this.idUsuario = idUsuario;
        this.descripcionBolsillo = descripcionBosillo;
        this.valorBolsillo = valorBolsillo;
    }

    public int getIdBolsillo() {
        return idBolsillo;
    }

    public void setIdBolsillo(int idBolsillo) {
        this.idBolsillo = idBolsillo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcionBolsillo() {
        return descripcionBolsillo;
    }

    public void setDescripcionBolsillo(String descripcionBosillo) {
        this.descripcionBolsillo = descripcionBosillo;
    }

    public int getValorBolsillo() {
        return valorBolsillo;
    }

    public void setValorBolsillo(int valorBolsillo) {
        this.valorBolsillo = valorBolsillo;
    }
}
