package com.clinica.recordatorios.urgent;
import com.clinica.recordatorios.domain.Cita;
public interface UrgentGateway { void enviar(Cita cita,String mensaje); }
