package com.clinica.recordatorios.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Cita {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private LocalDateTime fechaHora;
    private String correo;
    private String telefono;
    @Enumerated(EnumType.STRING) private CanalNotificacion canal;
    private boolean recordatorioEnviado;
    protected Cita() { }
    public Cita(LocalDateTime fechaHora, String correo, String telefono, CanalNotificacion canal) {
        this.fechaHora=fechaHora; this.correo=correo; this.telefono=telefono; this.canal=canal;
    }
    public void marcarRecordatorioEnviado() { recordatorioEnviado=true; }
    public Long getId(){return id;} public LocalDateTime getFechaHora(){return fechaHora;}
    public String getCorreo(){return correo;} public String getTelefono(){return telefono;}
    public CanalNotificacion getCanal(){return canal;} public boolean isRecordatorioEnviado(){return recordatorioEnviado;}
}
