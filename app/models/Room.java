package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Room extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer floor;
    public Integer roomNumber;
    public Integer bedsCount;
    public String bedType;
    @ManyToOne
    public Hotel hotel;

    public static Finder<Integer,Room> find = new Finder<>(Room.class);
}
