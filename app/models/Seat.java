package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seat extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer row;
    public Integer col;
    @ManyToOne
    public Reservation reservation;
    @ManyToMany
    @JoinTable(name = "SeatEntertainment")
    public List<Entertainment> entertainments;
    @ManyToMany
    @JoinTable(name = "SeatMeal")
    public List<Meal> meals;
    @ManyToOne
    public FlightClass flightClass;

    public static Finder<Integer,Seat> find = new Finder<>(Seat.class);
}
