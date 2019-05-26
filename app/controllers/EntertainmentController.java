package controllers;

import models.Entertainment;
import models.Hotel;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class EntertainmentController extends Controller {

    public Result listEntertainments(){

        List<Entertainment> entertainmentList  = Entertainment.find.all();

        return ok(views.html.Entertainment.entertainments.render(entertainmentList));
    }

}