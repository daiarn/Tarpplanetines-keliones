package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Seat extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer row;
    public Integer colum;
    @ManyToOne
    public Reservation reservation;
    @ManyToMany
    public Entertainment entertainment;
    @ManyToMany
    public Meal meal;
    @ManyToOne
    public FlightClass flightClass;

    public static Finder<Integer,Seat> find = new Finder<>(Seat.class);
}
