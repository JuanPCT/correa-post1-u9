package com.clinica.recordatorios.service;
import com.clinica.recordatorios.channel.ReminderChannelFactory;
import com.clinica.recordatorios.domain.Cita;
import com.clinica.recordatorios.persistence.CitaRepository;
import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CitaReminderServiceImpl implements CitaReminderService {
    private final CitaRepository repository; private final ReminderChannelFactory factory; private final Clock clock;
    public CitaReminderServiceImpl(CitaRepository repository,ReminderChannelFactory factory,Clock clock){
        this.repository=repository; this.factory=factory; this.clock=clock;
    }
    @Transactional public int recordarProximasCitas(){
        LocalDateTime ahora=LocalDateTime.now(clock);
        var citas=repository.findByFechaHoraBetweenAndRecordatorioEnviadoFalse(ahora,ahora.plusHours(24));
        for(Cita cita:citas) enviarYRegistrar(cita);
        return citas.size();
    }
    private void enviarYRegistrar(Cita cita){
        factory.para(cita.getCanal()).enviar(cita);
        cita.marcarRecordatorioEnviado(); repository.save(cita);
    }
}
