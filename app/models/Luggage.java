package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Luggage extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String dementions;
    public Integer weight;
    public String contents;
    @ManyToOne
    public Vechile vechile;

    public static Finder<Integer,Luggage> find = new Finder<>(Luggage.class);
}
