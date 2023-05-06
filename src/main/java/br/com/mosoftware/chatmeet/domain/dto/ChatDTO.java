package br.com.mosoftware.chatmeet.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatDTO {

    private Long id;

    private List<UserDTO> users;

    private List<MessageDTO> messages;

}
