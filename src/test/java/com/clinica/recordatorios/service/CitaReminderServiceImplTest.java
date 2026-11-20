package com.clinica.recordatorios.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.clinica.recordatorios.channel.*;
import com.clinica.recordatorios.domain.*;
import com.clinica.recordatorios.persistence.CitaRepository;
import java.time.*;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class CitaReminderServiceImplTest {
    @Mock CitaRepository repository; @Mock ReminderChannelFactory factory; @Mock ReminderChannel email;
    CitaReminderServiceImpl service;
    final Clock clock=Clock.fixed(Instant.parse("2026-11-20T12:00:00Z"),ZoneOffset.UTC);
    @BeforeEach void setUp(){service=new CitaReminderServiceImpl(repository,factory,clock);}
    @Test void fijaVentanaYEnviaElCanalCorrespondiente(){
        Cita cita=cita();
        when(repository.findByFechaHoraBetweenAndRecordatorioEnviadoFalse(LocalDateTime.of(2026,11,20,12,0),LocalDateTime.of(2026,11,21,12,0))).thenReturn(List.of(cita));
        when(factory.para(CanalNotificacion.EMAIL)).thenReturn(email);
        assertEquals(1,service.recordarProximasCitas()); verify(email).enviar(cita); verify(repository).save(cita);
        assertTrue(cita.isRecordatorioEnviado());
    }
    @Test void listaVaciaNoInvocaCanalesNiGuarda(){
        when(repository.findByFechaHoraBetweenAndRecordatorioEnviadoFalse(any(),any())).thenReturn(List.of());
        assertEquals(0,service.recordarProximasCitas()); verifyNoInteractions(factory); verify(repository,never()).save(any());
    }
    @Test void falloDeCanalNoMarcaNiGuarda(){
        Cita cita=cita(); when(repository.findByFechaHoraBetweenAndRecordatorioEnviadoFalse(any(),any())).thenReturn(List.of(cita));
        when(factory.para(CanalNotificacion.EMAIL)).thenReturn(email); doThrow(new IllegalStateException()).when(email).enviar(cita);
        assertThrows(IllegalStateException.class,service::recordarProximasCitas);
        assertFalse(cita.isRecordatorioEnviado()); verify(repository,never()).save(cita);
    }
    private Cita cita(){return new Cita(LocalDateTime.of(2026,11,20,18,0),"a@b.co","300",CanalNotificacion.EMAIL);}
}
