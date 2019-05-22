package models;


import io.ebean.*;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Hotel extends Model {

    @Id
    public Integer id;
    @Constraints.Required
    public String name;
    public String address;

    public static Finder<Integer,Hotel> find = new Finder<>(Hotel.class);
}
