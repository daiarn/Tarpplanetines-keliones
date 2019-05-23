package models;


import io.ebean.*;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Hotel extends Model {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String address;

    public static Finder<Integer,Hotel> find = new Finder<>(Hotel.class);
}
