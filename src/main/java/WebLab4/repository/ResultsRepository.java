package WebLab4.repository;

import WebLab4.entity.ResultDB;
import WebLab4.pojo.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultsRepository extends JpaRepository<ResultDB, Long> {
    List<Result> findAllByLogin(String login);

    void deleteAllByLogin(String login);
}
