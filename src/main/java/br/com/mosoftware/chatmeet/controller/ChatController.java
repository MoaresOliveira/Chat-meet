package br.com.mosoftware.chatmeet.controller;

import br.com.mosoftware.chatmeet.domain.form.MessageForm;
import br.com.mosoftware.chatmeet.domain.form.UserForm;
import br.com.mosoftware.chatmeet.domain.mapper.ChatMapper;
import br.com.mosoftware.chatmeet.domain.model.Chat;
import br.com.mosoftware.chatmeet.domain.model.Message;
import br.com.mosoftware.chatmeet.domain.model.User;
import br.com.mosoftware.chatmeet.service.EventEmitterService;
import br.com.mosoftware.chatmeet.service.impl.ChatService;
import br.com.mosoftware.chatmeet.service.impl.MessageService;
import br.com.mosoftware.chatmeet.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping( "/api/chat" )
public class ChatController {

    private final MessageService messageService;

    private final UserService userService;

    private final EventEmitterService eventEmitter;

    private final ChatService chatService;

    private final ChatMapper mapper = ChatMapper.INSTANCE;

    // Pegar as informações do Chat
    @GetMapping( "/{chatId}" )
    public ResponseEntity<?> getChat( @PathVariable Long chatId ) {
        Chat chat = chatService.getById( chatId );
        return ResponseEntity.ok( mapper.toDto( chat ) );
    }

    // Listar mensagens do chat
    @GetMapping( "/messages/{chatId}" )
    public ResponseEntity<?> getChatMessages( @PathVariable Long chatId ) {
        List<Message> messages = chatService.getMessages( chatId );
        return ResponseEntity.ok( mapper.messageDtoList( messages ) );
    }

    // Listar os usuários do chat
    @GetMapping( "/users/{chatId}" )
    public ResponseEntity<?> getChatUsers( @PathVariable Long chatId ) {
        List<User> users = chatService.getUsers( chatId );
        return ResponseEntity.ok( mapper.userDtoList( users ) );
    }

    // Criar um chat com o usuário inicial
    @PostMapping( "/new" )
    public ResponseEntity<?> newChat( @Valid @RequestBody UserForm form ) {
        Chat chat = chatService.create( new Chat() );
        chat = addUser( chat, form.getNickname() );
        return ResponseEntity.ok( mapper.toDto( chat ) );
    }

    // Entrar em um chat existente
    @PostMapping( "/enter/{chatId}" )
    public ResponseEntity<?> enterChat( @PathVariable Long chatId, @Valid @RequestBody UserForm form ) {
        Chat chat = chatService.getById( chatId );
        chat = addUser( chat, form.getNickname() );
        return ResponseEntity.ok( mapper.toDto( chat ) );
    }

    // Enviar mensagens em um chat
    @PostMapping( "/message/{chatId}" )
    public ResponseEntity<?> messageChat( @PathVariable Long chatId, @Valid @RequestBody MessageForm form ) {
        Chat chat = chatService.getById( chatId );
        chat = addMessage( chat, form );
        return ResponseEntity.ok( mapper.toDto( chat ) );
    }

    // Sair do chat
    @PutMapping( "/leave/{chatId}/{userId}" )
    public ResponseEntity<?> leaveChat( @PathVariable Long chatId, @PathVariable Long userId ) {
        Chat chat = chatService.getById( chatId );
        User user = userService.getById( userId );
        chat = removeUser( chat, user );
        if ( chat == null ) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        return eventEmitter.subscribe();
    }


    private Chat addUser( Chat chat, String nickname ) {
        User user = new User();
        user.setNickname( nickname );
        user.setChat( chat );
        User savedUser = userService.create( user );
        return chatService.addUser( chat.getId(), savedUser );
    }

    private Chat removeUser( Chat chat, User user ) {
        userService.delete( user.getId() );
        return chatService.removeUser( chat.getId(), user );
    }

    private Chat addMessage( Chat chat, MessageForm form ) {
        User user = userService.getById( form.getUserId() );
        Message message =
                Message.builder()
                        .message( form.getMessage() )
                        .chat( chat )
                        .author( user.getNickname() )
                        .build();
        message = messageService.create( message );
        eventEmitter.dispatchEvents(
                SseEmitter.event()
                        .name( "message" )
                        .data( mapper.messageDto( message ) )
        );
        return chatService.addMessage( chat.getId(), message );
    }
}
