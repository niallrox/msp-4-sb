package WebLab4.controllers;

import WebLab4.exceptions.ValidationException;
import WebLab4.pojo.ErrorResponse;
import WebLab4.pojo.Point;
import WebLab4.pojo.Result;
import WebLab4.services.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points")
@CrossOrigin("http://localhost:4200/")
public class MainController {

    private ResultsService resultsService;

    @Autowired
    public MainController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse similarUserException(ValidationException ex) {
        return new ErrorResponse(ex.getMessage(), "404");
    }

    @GetMapping
    public List<Result> getResult() {
        return resultsService.getResults();
    }

    @PostMapping
    public Result sendPoint(@RequestBody Point point) {
        return resultsService.addResult(point);
    }

    @PostMapping
    public void resetPoint() {
        resultsService.clearResult();
    }
}
