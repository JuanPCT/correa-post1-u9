package com.clinica.recordatorios.channel;
import com.clinica.recordatorios.domain.CanalNotificacion;
import java.util.*;
import org.springframework.stereotype.Component;
@Component
public class ReminderChannelFactory {
    private final Map<CanalNotificacion,ReminderChannel> canales=new EnumMap<>(CanalNotificacion.class);
    public ReminderChannelFactory(List<ReminderChannel> items){items.forEach(c->canales.put(c.canal(),c));}
    public ReminderChannel para(CanalNotificacion canal){
        ReminderChannel seleccionado=canales.get(canal);
        if(seleccionado==null) throw new IllegalArgumentException("Canal no configurado: "+canal);
        return seleccionado;
    }
}
