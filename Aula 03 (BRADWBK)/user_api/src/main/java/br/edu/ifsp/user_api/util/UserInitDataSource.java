package br.edu.ifsp.user_api.util;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsp.user_api.model.User;

@Component
public class UserInitDataSource implements CommandLineRunner {
    private final UserDataSource uDataSource;

    public UserInitDataSource(UserDataSource uDataSource) {
        this.uDataSource = uDataSource;
    }

    int countId = 1;

    @Override
    public void run(String... args) throws Exception {   
        Stream.generate(() -> {
            User user = new User();
            user.setId(++countId); 
            user.setLogin("IFSP " + user.getId()); 
            user.setPassword("1234"); 
            return user;
        }).limit(20).forEach(uDataSource::add);
    }
}
