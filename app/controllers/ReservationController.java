package controllers;

import models.Reservation;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class ReservationController extends Controller {

    public Result listVoyage(){

        List<Reservation> voyageList = Reservation.find.all();

        return ok(views.html.Reservation.reservations.render(voyageList));
    }

    public Result getReservation(Integer id) {

        Reservation reservation = Reservation.find.byId(id);

        return ok(views.html.Reservation.reservationInfo.render(reservation));
    }
}
