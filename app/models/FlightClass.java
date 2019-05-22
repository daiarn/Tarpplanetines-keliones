package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class FlightClass extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String Type;
    public Integer SeatCount;

    public static Finder<Integer,FlightClass> find = new Finder<>(FlightClass.class);
}
