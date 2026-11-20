package com.clinica.recordatorios.channel;
import org.slf4j.*;
import org.springframework.stereotype.Component;
@Component
public class ConsolaEmailGateway implements EmailGateway {
    private static final Logger LOG=LoggerFactory.getLogger(ConsolaEmailGateway.class);
    public void enviar(String destino,String mensaje){LOG.info("EMAIL simulado para {}: {}",destino,mensaje);}
}
