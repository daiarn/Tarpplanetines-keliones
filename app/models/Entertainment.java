package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Entertainment extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String discription;
    public Double price;
    @ManyToMany
    public Seat seat;

    public static Finder<Integer,Entertainment> find = new Finder<>(Entertainment.class);
}
