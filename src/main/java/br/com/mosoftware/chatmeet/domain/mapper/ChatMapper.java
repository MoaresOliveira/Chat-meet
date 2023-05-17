package br.com.mosoftware.chatmeet.domain.mapper;

import br.com.mosoftware.chatmeet.domain.dto.ChatDTO;
import br.com.mosoftware.chatmeet.domain.dto.MessageDTO;
import br.com.mosoftware.chatmeet.domain.dto.UserDTO;
import br.com.mosoftware.chatmeet.domain.model.Chat;
import br.com.mosoftware.chatmeet.domain.model.Message;
import br.com.mosoftware.chatmeet.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper( ChatMapper.class );

    ChatDTO toDto( Chat entity );

    MessageDTO messageDto( Message entity );

    Chat toEntity( ChatDTO dto );

    List<Chat> toListEntity( List<ChatDTO> dtoList );

    List<ChatDTO> toListDto( List<Chat> entityList );

    List<MessageDTO> messageDtoList( List<Message> entityList );

    List<UserDTO> userDtoList( List<User> entityList );

}
