package com.clinica.recordatorios.urgent;
import com.clinica.recordatorios.channel.SmsGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
@Configuration
public class CanalUrgenteConfig {
    @Bean @Qualifier("canalUrgente")
    UrgentGateway canalUrgente(SmsGateway sms){return (cita,mensaje)->sms.enviar(cita.getTelefono(),mensaje);}
}
