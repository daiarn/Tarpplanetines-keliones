package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VoyagePrice extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Date calculationDate;
    public Double distanceFromEarth;
    @ManyToOne
    public VechileSpeed speed;

    public static Finder<Integer,VoyagePrice> find = new Finder<>(VoyagePrice.class);
}
