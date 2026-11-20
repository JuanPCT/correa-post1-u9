package com.clinica.recordatorios.config;
import java.time.Clock;
import org.springframework.context.annotation.*;
@Configuration
public class ClockConfig { @Bean Clock clock(){return Clock.systemDefaultZone();} }
