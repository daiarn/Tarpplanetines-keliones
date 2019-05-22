package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class VoyagePrice extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Date calculationDate;
    public Double distanceFromEarth;
    public VechileSpeed speed;

    public static Finder<Integer,VoyagePrice> find = new Finder<>(VoyagePrice.class);
}
