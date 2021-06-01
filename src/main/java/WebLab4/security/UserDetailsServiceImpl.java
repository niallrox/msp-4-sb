package WebLab4.security;

import WebLab4.entity.UserDB;
import WebLab4.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDB userDB = usersRepository.findByLogin(login);
        if (userDB == null) {
            throw new UsernameNotFoundException("User doesn't exist");
        } else {
            return new SecurityUser(userDB);
        }
    }
}
