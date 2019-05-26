package controllers;

import models.Hotel;
import models.Meal;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class MealController extends Controller {

    public Result listMeals(){

        List<Meal> mealList = Meal.find.all();

        return ok(views.html.Meal.meals.render(mealList));
    }

}
