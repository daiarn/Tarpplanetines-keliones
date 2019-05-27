package controllers;

import models.Reservation;
import models.VechileSpeed;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class ReservationController extends Controller {

    private final FormFactory formFactory;

    @Inject
    public ReservationController(final FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result listVoyage(){

        List<Reservation> voyageList = Reservation.find.all();

        return ok(views.html.Reservation.reservations.render(voyageList));
    }

    public Result getReservation(Integer id) {

        Reservation reservation = Reservation.find.byId(id);

        return ok(views.html.Reservation.reservationInfo.render(reservation));
    }

    public Result getReservationPrice(Integer id) {

        Reservation reservation = Reservation.find.byId(id);
        List<VechileSpeed> vechileSpeeds = VechileSpeed.find.all();
        double price = reservation.finalPrice;

        return ok(views.html.Reservation.reservationPrice.render(reservation, vechileSpeeds, price));
    }
    public Result calcReservationPrice(Integer id) {

        Reservation reservation = Reservation.find.byId(id);
        List<VechileSpeed> vechileSpeeds = VechileSpeed.find.all();

        DynamicForm requestData = formFactory.form().bindFromRequest();
        double distance = Double.parseDouble(requestData.get("distanceFromEarth"));
        Integer speed = Integer.valueOf(requestData.get("speed"));

        double newPrice = VoyagePriceController.calculateNewPrice(reservation, speed, distance);



        return ok(views.html.Reservation.reservationPrice.render(reservation, vechileSpeeds, newPrice));
    }
}
