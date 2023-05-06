package br.com.mosoftware.chatmeet.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "TB_CHAT" )
public class Chat {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @OneToMany( mappedBy = "chat", cascade = CascadeType.REMOVE )
    private List<User> users = new ArrayList<>();

    @OneToMany( mappedBy = "chat", cascade = CascadeType.REMOVE )
    private List<Message> messages = new ArrayList<>();

    public void addUser( User user ) {
        this.users.add( user );
    }

    public void removeUser( User user ) {
        this.users.remove( user );
    }

    public void addMessage( Message message ) {
        this.messages.add( message );
    }


}
