package com.clinica.recordatorios.urgent;
public class CitaNoEncontradaException extends RuntimeException {
    public CitaNoEncontradaException(Long id){super("No existe la cita "+id);}
}
