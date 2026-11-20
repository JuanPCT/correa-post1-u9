package com.clinica.recordatorios.channel;
import com.clinica.recordatorios.domain.CanalNotificacion;
import com.clinica.recordatorios.domain.Cita;
import org.springframework.stereotype.Component;
@Component
public class EmailReminderChannel implements ReminderChannel {
    private final EmailGateway gateway;
    public EmailReminderChannel(EmailGateway gateway){this.gateway=gateway;}
    public CanalNotificacion canal(){return CanalNotificacion.EMAIL;}
    public void enviar(Cita cita){gateway.enviar(cita.getCorreo(),"Recordatorio: su cita es "+cita.getFechaHora());}
}
