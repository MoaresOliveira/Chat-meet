package br.com.mosoftware.chatmeet.service.impl;

import br.com.mosoftware.chatmeet.domain.model.Message;
import br.com.mosoftware.chatmeet.exception.NotFoundException;
import br.com.mosoftware.chatmeet.repository.MessageRepository;
import br.com.mosoftware.chatmeet.service.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService implements IService<Message, Long> {

    private final MessageRepository repository;

    @Override
    public Message create( Message input ) {
        input.setMoment( Instant.now() );
        return repository.save( input );
    }

    @Override
    public Message getById( Long id ) {
        return findByIdOrFail( id );
    }

    @Override
    public List<Message> getAll() {
        return repository.findAll();
    }

    private Message findByIdOrFail( Long id ) {
        return repository.findById( id )
                .orElseThrow( () -> new NotFoundException( Message.class, id ) );
    }
}
