package com.clinica.recordatorios.channel;
import org.slf4j.*;
import org.springframework.stereotype.Component;
@Component
public class ConsolaSmsGateway implements SmsGateway {
    private static final Logger LOG=LoggerFactory.getLogger(ConsolaSmsGateway.class);
    public void enviar(String telefono,String mensaje){LOG.info("SMS simulado para {}: {}",telefono,mensaje);}
}
