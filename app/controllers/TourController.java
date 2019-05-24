package controllers;

import models.Hotel;
import models.Tour;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class TourController extends Controller {

    public Result listTours(){

        List<Tour> tourList = Tour.find.all();

        return ok(views.html.Tour.tours.render(tourList));
    }

    public Result getTour(Integer id){

        Tour tour = Tour.find.byId(id);

        return ok(views.html.Tour.tour.render(tour));

    }

}
