package controllers;

import models.Reservation;
import models.VechileSpeed;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
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
    public List<Reservation> getMyReservations() {

        List<Reservation> voyageList = Reservation.find.all();
        List<Reservation> myVoyageList = new ArrayList<>();

        for (Reservation r : voyageList) {
            try {
                if (r.myReservation.id == 1) {
                    myVoyageList.add(r);
                }
            }
            catch (NullPointerException e){
                //do nothing
            }
        }
        return  myVoyageList;
    }

    public Result showMyReservations() {

        List<Reservation> myVoyageList = getMyReservations();

        return ok(views.html.Reservation.myReservations.render(myVoyageList));
    }

    public Result cancelReservation(Integer id) {

        Reservation reservation = Reservation.find.byId(id);
        try {
            if (reservation != null) {
                if (reservation.state.id.equals(1) || reservation.state.id.equals(4)){
                    Reservation newReservation = new Reservation();
                    newReservation.id = reservation.id;
                    newReservation.nr = reservation.nr;
                    newReservation.Date = reservation.Date;
                    newReservation.takeOffPlace = reservation.takeOffPlace;
                    newReservation.finalPrice = reservation.finalPrice;
                    newReservation.PaymentDate = reservation.PaymentDate;
                    newReservation.takeOffDate = reservation.takeOffDate;
                    newReservation.arrivingDate = reservation.arrivingDate;
                    newReservation.comeBackDate = reservation.comeBackDate;
                    newReservation.state = reservation.state;
                    newReservation.hotel = reservation.hotel;
                    newReservation.vechile = reservation.vechile;

                    reservation.delete();
                    newReservation.save();
                }
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        List<Reservation> myVoyageList = getMyReservations();

        return ok(views.html.Reservation.myReservations.render(myVoyageList));
    }
}
