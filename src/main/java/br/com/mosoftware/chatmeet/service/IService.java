package br.com.mosoftware.chatmeet.service;

import java.util.List;

public interface IService<T, Identifier> {

    T create( T input );

    T getById( Identifier id );

    List<T> getAll();

}
