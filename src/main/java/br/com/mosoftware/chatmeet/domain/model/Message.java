package br.com.mosoftware.chatmeet.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table( name = "TB_MESSAGE" )
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String message;

    private Instant moment;

    private String author;

    @ManyToOne
    private Chat chat;

}
