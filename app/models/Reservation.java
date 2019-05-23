package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String nr;
    public Date Date;
    public String takeOffPlace;
    public Double finalPrice;
    public Date PaymentDate;
    public Date takeOffDate;
    public Date arrivingDate;
    public Date comeBackDate;
    @ManyToOne
    public ReservationState state;
    @ManyToOne
    public Hotel hotel;
    @ManyToOne
    public Vechile vechile;

    public static Finder<Integer,Reservation> find = new Finder<>(Reservation.class);
}
