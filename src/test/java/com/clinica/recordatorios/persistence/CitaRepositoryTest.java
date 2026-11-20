package com.clinica.recordatorios.persistence;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.clinica.recordatorios.domain.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest
class CitaRepositoryTest {
    @Autowired CitaRepository repository;
    @Test void consultaFiltraRangoYEstado(){
        repository.save(new Cita(LocalDateTime.of(2026,11,20,18,0),"a@b.co","300",CanalNotificacion.EMAIL));
        Cita enviada=new Cita(LocalDateTime.of(2026,11,20,19,0),"b@b.co","301",CanalNotificacion.SMS); enviada.marcarRecordatorioEnviado(); repository.save(enviada);
        repository.save(new Cita(LocalDateTime.of(2026,11,22,9,0),"c@b.co","302",CanalNotificacion.SMS));
        assertEquals(1,repository.findByFechaHoraBetweenAndRecordatorioEnviadoFalse(LocalDateTime.of(2026,11,20,12,0),LocalDateTime.of(2026,11,21,12,0)).size());
    }
}
