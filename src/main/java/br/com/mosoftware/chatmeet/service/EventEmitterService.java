package br.com.mosoftware.chatmeet.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class EventEmitterService {

    public List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    // Se inscrever para receber eventos
    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send( SseEmitter.event().name( "INIT" ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        sseEmitter.onCompletion( () -> emitters.remove( sseEmitter ) );
        emitters.add( sseEmitter );
        return sseEmitter;
    }

    // Enviar eventos
    public void dispatchEvents ( SseEmitter.SseEventBuilder builder ) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send( builder );
            } catch ( IOException ex ) {
                emitters.remove( emitter );
            }
        }
    }

}
