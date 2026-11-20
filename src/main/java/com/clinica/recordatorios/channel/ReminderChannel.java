package com.clinica.recordatorios.channel;
import com.clinica.recordatorios.domain.CanalNotificacion;
import com.clinica.recordatorios.domain.Cita;
public interface ReminderChannel { CanalNotificacion canal(); void enviar(Cita cita); }
