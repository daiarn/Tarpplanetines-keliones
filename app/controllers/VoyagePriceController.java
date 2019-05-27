package controllers;

import models.Reservation;
import play.mvc.Controller;

public class VoyagePriceController extends Controller {

    public static double calculateNewPrice(Reservation reservation, Integer speed, double distance) {

        Integer vechileId = reservation.vechile.id;

        Integer speedInpact = 5 - speed;

        return distance * 0.005 * vechileId * speedInpact;
    }
}
