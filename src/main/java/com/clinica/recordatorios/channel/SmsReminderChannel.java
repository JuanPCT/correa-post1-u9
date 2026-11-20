package com.clinica.recordatorios.channel;
import com.clinica.recordatorios.domain.CanalNotificacion;
import com.clinica.recordatorios.domain.Cita;
import org.springframework.stereotype.Component;
@Component
public class SmsReminderChannel implements ReminderChannel {
    private final SmsGateway gateway;
    public SmsReminderChannel(SmsGateway gateway){this.gateway=gateway;}
    public CanalNotificacion canal(){return CanalNotificacion.SMS;}
    public void enviar(Cita cita){gateway.enviar(cita.getTelefono(),"Su cita es "+cita.getFechaHora());}
}
