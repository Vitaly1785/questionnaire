package ru.petukhov.questionnaire.Config.Security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.petukhov.questionnaire.Entity.Person;
import ru.petukhov.questionnaire.Repo.PersonRepo;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final PersonRepo personRepo;

    public UserService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> personOptional = personRepo.findByLogin(s);
        if (!personOptional.isPresent()){
            throw new UsernameNotFoundException("user with this username was not found");
        }
        Person person = personOptional.get();
        return new User(person.getLogin(), person.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(person.getRole().getName())));
    }
}
