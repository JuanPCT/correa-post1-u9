package com.clinica.recordatorios.web;
import com.clinica.recordatorios.service.CitaReminderService;
import com.clinica.recordatorios.urgent.NotificadorUrgente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class RecordatorioController {
    private final CitaReminderService reminder; private final NotificadorUrgente urgente;
    public RecordatorioController(CitaReminderService reminder,NotificadorUrgente urgente){this.reminder=reminder;this.urgente=urgente;}
    @PostMapping("/api/recordatorios") public ResponseEntity<Integer> enviarPendientes(){return ResponseEntity.ok(reminder.recordarProximasCitas());}
    @PostMapping("/api/citas/{id}/urgente") public ResponseEntity<Void> urgente(@PathVariable Long id,@RequestParam String mensaje){
        urgente.notificar(id,mensaje); return ResponseEntity.noContent().build();
    }
}
