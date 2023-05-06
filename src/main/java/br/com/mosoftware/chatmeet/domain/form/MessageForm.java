package br.com.mosoftware.chatmeet.domain.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MessageForm {

    @NotBlank
    private String message;

    @NotNull
    @Min( 1 )
    private Long userId;

}
