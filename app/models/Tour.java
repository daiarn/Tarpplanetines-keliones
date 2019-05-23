package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Tour extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String description;
    public Double price;
    public Integer seatCount;
    @ManyToOne
    public Hotel hotel;

    public static Finder<Integer,Tour> find = new Finder<>(Tour.class);
}
