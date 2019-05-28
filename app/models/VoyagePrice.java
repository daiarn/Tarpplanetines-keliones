package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VoyagePrice extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Date calculationDate;
    @Constraints.Min(1)
    public Double distanceFromEarth;
    @ManyToOne
    public VechileSpeed speed;
    @ManyToOne
    public Reservation reservation;

    public static Finder<Integer,VoyagePrice> find = new Finder<>(VoyagePrice.class);
}
