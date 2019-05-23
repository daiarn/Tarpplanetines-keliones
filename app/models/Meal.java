package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Meal extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String description;
    public Double price;
    public Date dayOfTheWeek;
    @ManyToMany
    public Seat seat;

    public static Finder<Integer,Meal> find = new Finder<>(Meal.class);
}
