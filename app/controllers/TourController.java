package controllers;

import models.Hotel;
import models.Tour;
import models.Vechile;
import models.VoyagePrice;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

import static jdk.nashorn.internal.objects.NativeMath.round;

public class TourController extends Controller {

    private Double calculatePrice(VoyagePrice voyagePrice){

        Properties properties = new Properties();

        try {
            properties.load(new FileReader(new File("./conf/priceCoefficient.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 55.9999999;
    }


    public Result listTours(){

        List<Tour> tourList = Tour.find.all();

        return ok(views.html.Tour.tours.render(tourList));
    }

    public Result getTour(Integer id){

        Tour tour = Tour.find.byId(id);

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        Double calculatedPrice = calculatePrice(new VoyagePrice());

        tour.price = Double.parseDouble(df.format(calculatedPrice));

        return ok(views.html.Tour.tour.render(tour));

    }

}
