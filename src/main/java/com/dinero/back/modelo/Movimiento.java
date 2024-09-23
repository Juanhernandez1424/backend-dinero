package com.dinero.back.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabla_movimiento")
public class Movimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private int idMovimiento;
    private int idUsuario;
    private int idTipoMovimiento;
    private String descripcionMovimiento;
    private String fechaMovimiento;
    private int valorMovimiento;

    public Movimiento() {
    }

    public Movimiento(int idMovimiento, int idUsuario, int idTipoMovimiento, String descripcionMovimiento, String fechaMovimiento, int valorMovimiento) {
        this.idMovimiento = idMovimiento;
        this.idUsuario = idUsuario;
        this.idTipoMovimiento = idTipoMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.valorMovimiento = valorMovimiento;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getValorMovimiento() {
        return valorMovimiento;
    }

    public void setValorMovimiento(int valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }
}
