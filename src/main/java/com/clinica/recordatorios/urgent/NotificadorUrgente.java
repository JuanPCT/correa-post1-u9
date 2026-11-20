package com.clinica.recordatorios.urgent;
import com.clinica.recordatorios.domain.Cita;
import com.clinica.recordatorios.persistence.CitaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class NotificadorUrgente {
    private final CitaRepository repository; private final UrgentGateway gateway;
    public NotificadorUrgente(CitaRepository repository,@Qualifier("canalUrgente") UrgentGateway gateway){
        this.repository=repository; this.gateway=gateway;
    }
    @Transactional public void notificar(Long id,String mensaje){
        Cita cita=repository.findById(id).orElseThrow(()->new CitaNoEncontradaException(id));
        gateway.enviar(cita,mensaje);
        cita.marcarRecordatorioEnviado(); repository.save(cita);
    }
}
