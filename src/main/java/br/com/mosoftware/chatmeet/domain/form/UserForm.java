package br.com.mosoftware.chatmeet.domain.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotNull
    @Size(min = 2, max = 16)
    private String nickname;

}
