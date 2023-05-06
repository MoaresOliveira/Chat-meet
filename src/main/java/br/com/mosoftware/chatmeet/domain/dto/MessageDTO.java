package br.com.mosoftware.chatmeet.domain.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class MessageDTO {

    private Long id;

    private String message;

    private Instant moment;

    private String author;

}
