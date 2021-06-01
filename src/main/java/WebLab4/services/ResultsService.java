package WebLab4.services;

import WebLab4.entity.ResultDB;
import WebLab4.exceptions.ValidationException;
import WebLab4.pojo.Point;
import WebLab4.pojo.Result;
import WebLab4.repository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.DoubleStream;

@Service
@Transactional
public class ResultsService {

    private ResultsRepository resultsRepository;
    private Authentication auth;

    @Autowired
    public ResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    public List<Result> getResults() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        return resultsRepository.findAllByLogin(auth.getName());
    }

    public void clearResult() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        this.resultsRepository.deleteAllByLogin(auth.getName());
    }

    public Result addResult(Point point) throws ValidationException {
        double y = point.getY();
        double x = point.getX();
        double r = point.getR();
        if (x <= 3 && x >= -5 && y <= 3 && y >= -3 && DoubleStream.of(1, 1.5, 2, 2.5, 3).anyMatch(a -> a == r)) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss"));
            String resultOfCheck = checkArea(x, y, r);
            Result result = new Result(y, x, r, resultOfCheck, time);
            resultsRepository.save(fromResultToResultDB(result));
            return result;
        } else {
            throw new ValidationException("Validation error");
        }
    }

    private String checkArea(double x, double y, double r) {
        if (x < 0 && y > 0 && x > -r && y < r) {
            return "true";
        }
        if (x > 0 && y > 0 && y <= -2 * x + r) {
            return "true";
        }
        if (x >= 0 && y <= 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) {
            return "true";
        }
        return "false";
    }

    private ResultDB fromResultToResultDB(Result result) {
        ResultDB resultDB = new ResultDB();
        resultDB.setY(result.getY());
        resultDB.setX(result.getX());
        resultDB.setR(result.getR());
        resultDB.setResult(result.getResult());
        resultDB.setTime(result.getTime());
        auth = SecurityContextHolder.getContext().getAuthentication();
        resultDB.setLogin(auth.getName());
        return resultDB;
    }
}
