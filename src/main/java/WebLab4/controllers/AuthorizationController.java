package WebLab4.controllers;

import WebLab4.entity.UserDB;
import WebLab4.exceptions.SimilarUserException;
import WebLab4.exceptions.ValidationException;
import WebLab4.pojo.ErrorResponse;
import WebLab4.pojo.Response;
import WebLab4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200/")
public class AuthorizationController {

    private UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(SimilarUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse similarUserException(SimilarUserException ex) {
        return new ErrorResponse(ex.getMessage(), "400");
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse similarUserException(ValidationException ex) {
        return new ErrorResponse(ex.getMessage(), "400");
    }

    @GetMapping("/authorization")
    private Response authorization() {
        return new Response("Вы успешно авторизованны");
    }

    @PostMapping("/registration")
    private Response registration(@RequestBody UserDB userDB) {
        return userService.registration(userDB);
    }
}