package controllers;

import models.Hotel;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Arrays;
import java.util.List;

public class HotelController extends Controller {

    public Result listHotels(){
        Hotel hotel1 = new Hotel("Olympia", "Greece");
        Hotel hotel2 = new Hotel("Grand", "Germany");
        Hotel hotel3 = new Hotel("Fisher", "France");
        Hotel hotel4 = new Hotel("OldTown", "Lithuania");

        List<Hotel> hotels = Arrays.asList(hotel1,hotel2,hotel3,hotel4);

        return ok(views.html.hotels.render(hotels));
    }

}
