package controllers;

import models.Hotel;
import models.Room;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class HotelController extends Controller {

    public Result listHotels(){

        List<Hotel> hotels = Hotel.find.all();

        return ok(views.html.Hotel.hotels.render(hotels));
    }


    public Result getHotelForm() {

        List<Hotel> hotels = Hotel.find.all();
        List<Room> rooms = Room.find.all();

        return ok(views.html.Order.selectHotel.render(hotels,rooms));
    }

}
