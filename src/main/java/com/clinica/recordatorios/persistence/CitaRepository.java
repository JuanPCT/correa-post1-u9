package com.clinica.recordatorios.persistence;
import com.clinica.recordatorios.domain.Cita;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CitaRepository extends JpaRepository<Cita,Long> {
    List<Cita> findByFechaHoraBetweenAndRecordatorioEnviadoFalse(LocalDateTime desde, LocalDateTime hasta);
}
