package br.com.mosoftware.chatmeet.service.impl;

import br.com.mosoftware.chatmeet.domain.model.User;
import br.com.mosoftware.chatmeet.exception.NotFoundException;
import br.com.mosoftware.chatmeet.repository.UserRepository;
import br.com.mosoftware.chatmeet.service.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements IService<User, Long> {

    private final UserRepository repository;


    @Override
    public User create( User input ) {
        return repository.save( input );
    }

    @Override
    public User getById( Long id ) {
        return findByIdOrFail( id );
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    public void delete( Long id ) {
        User user = findByIdOrFail( id );
        repository.delete( user );
    }

    private User findByIdOrFail( Long id ) {
        return repository.findById( id )
                .orElseThrow( () -> new NotFoundException( User.class, id ) );
    }
}
