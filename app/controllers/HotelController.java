package controllers;

import models.Hotel;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class HotelController extends Controller {

    public Result listHotels(){

        List<Hotel> hotels = Hotel.find.all();

        return ok(views.html.hotels.render(hotels));
    }

}
