package WebLab4.services;

import WebLab4.entity.UserDB;
import WebLab4.exceptions.SimilarUserException;
import WebLab4.exceptions.ValidationException;
import WebLab4.pojo.Response;
import WebLab4.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UsersRepository usersRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UsersRepository usersRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRepository = usersRepository;
    }

    public Response registration(UserDB userDB) {
        if (validateUser(userDB)) {
            UserDB userDBDBFrom = usersRepository.findByLogin(userDB.getLogin());
            if (userDBDBFrom == null) {
                userDB.setPassword(bCryptPasswordEncoder.encode(userDB.getPassword()));
                usersRepository.save(userDB);
                return new Response("Пользователь " + userDB.getLogin() + " зарегестрирован");
            } else {
                throw new SimilarUserException("Such user is already exist");
            }
        } else {
            throw new ValidationException("Validation error");
        }
    }

    private boolean validateUser(UserDB userDB) {
        return userDB != null && userDB.getLogin() != null && !userDB.getLogin().isEmpty() && userDB.getPassword() != null && !userDB.getPassword().isEmpty();
    }
}