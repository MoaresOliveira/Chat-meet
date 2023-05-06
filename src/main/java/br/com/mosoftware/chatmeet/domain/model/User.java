package br.com.mosoftware.chatmeet.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table( name = "TB_USER" )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nickname;

    @ManyToOne
    private Chat chat;

}
