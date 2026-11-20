package com.clinica.recordatorios.urgent;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.clinica.recordatorios.domain.*;
import com.clinica.recordatorios.persistence.CitaRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class NotificadorUrgenteTest {
    @Mock CitaRepository repository; @Mock UrgentGateway gateway; NotificadorUrgente servicio;
    @BeforeEach void setUp(){servicio=new NotificadorUrgente(repository,gateway);}
    @Test void envioExitosoSePersiste(){
        Cita cita=cita(); when(repository.findById(4L)).thenReturn(Optional.of(cita));
        servicio.notificar(4L,"Cambio de consultorio"); verify(gateway).enviar(cita,"Cambio de consultorio"); verify(repository).save(cita);
    }
    @Test void falloExternoNoPersisteComoEnviado(){
        Cita cita=cita(); when(repository.findById(4L)).thenReturn(Optional.of(cita));
        doThrow(new IllegalStateException("gateway caído")).when(gateway).enviar(cita,"Urgente");
        assertThrows(IllegalStateException.class,()->servicio.notificar(4L,"Urgente")); verify(repository,never()).save(cita);
    }
    @Test void citaInexistenteNoLlamaAlGateway(){
        when(repository.findById(8L)).thenReturn(Optional.empty());
        assertThrows(CitaNoEncontradaException.class,()->servicio.notificar(8L,"Urgente")); verifyNoInteractions(gateway);
    }
    private Cita cita(){return new Cita(LocalDateTime.of(2026,11,21,9,0),"a@b.co","300",CanalNotificacion.SMS);}
}
