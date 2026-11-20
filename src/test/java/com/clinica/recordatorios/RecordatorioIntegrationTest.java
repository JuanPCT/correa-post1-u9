package com.clinica.recordatorios;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import com.clinica.recordatorios.channel.SmsGateway;
import com.clinica.recordatorios.domain.*;
import com.clinica.recordatorios.persistence.CitaRepository;
import com.clinica.recordatorios.service.CitaReminderService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
class RecordatorioIntegrationTest {
    @Autowired CitaRepository repository; @Autowired CitaReminderService service; @MockBean SmsGateway sms;
    @Test @Transactional void integraContextoJPAFactoryYCanal(){
        Cita cita=repository.save(new Cita(LocalDateTime.now().plusHours(1),"p@example.co","3001234567",CanalNotificacion.SMS));
        service.recordarProximasCitas();
        assertTrue(repository.findById(cita.getId()).orElseThrow().isRecordatorioEnviado());
        verify(sms).enviar("3001234567","Su cita es "+cita.getFechaHora());
    }
}
