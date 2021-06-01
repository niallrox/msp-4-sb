package WebLab4.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "results")
public class ResultDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double y;
    private double x;
    private double r;
    private String result;
    private String time;
    private String benchmark;
    private String login;
}