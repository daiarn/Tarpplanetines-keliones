package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Vechile extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public VechileFuel fuel;

    public static Finder<Integer,Vechile> find = new Finder<>(Vechile.class);
}
