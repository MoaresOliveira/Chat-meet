package br.com.mosoftware.chatmeet.service.impl;

import br.com.mosoftware.chatmeet.domain.mapper.ChatMapper;
import br.com.mosoftware.chatmeet.domain.model.Chat;
import br.com.mosoftware.chatmeet.domain.model.Message;
import br.com.mosoftware.chatmeet.domain.model.User;
import br.com.mosoftware.chatmeet.exception.NotFoundException;
import br.com.mosoftware.chatmeet.repository.ChatRepository;
import br.com.mosoftware.chatmeet.service.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService implements IService<Chat, Long> {

    private final ChatRepository repository;

    public List<Message> getMessages( Long id ) {
        Chat chat = findByIdOrFail( id );
        return chat.getMessages();
    }

    public List<User> getUsers( Long id ) {
        Chat chat = findByIdOrFail( id );
        return chat.getUsers();
    }

    public Chat addUser( Long id, User user ) {
        Chat chat = findByIdOrFail( id );
        chat.addUser( user );
        chat = repository.save( chat );
        return chat;
    }

    // Remove usuário do chat, se o usuário for o último o chat é deletado.
    public Chat removeUser( Long id, User user ) {
        Chat chat = findByIdOrFail( id );
        chat.removeUser( user );
        if(chat.getUsers().size() > 0){
            repository.save( chat );
            return chat;
        }else {
            repository.delete( chat );
            return null;
        }
    }

    public Chat addMessage( Long id, Message message ) {
        Chat chat = findByIdOrFail( id );
        chat.addMessage( message );
        chat = repository.save( chat );
        return chat;
    }

    @Override
    public Chat create( Chat input ) {
        return repository.save( input );
    }

    @Override
    public Chat getById( Long id ) {
        return findByIdOrFail( id );
    }

    @Override
    public List<Chat> getAll() {
       return repository.findAll();
    }

    private Chat findByIdOrFail( Long id ) {
        return repository.findById( id )
                .orElseThrow( () -> new NotFoundException( Chat.class, id ) );
    }
}
