import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Flight.Type;
import com.skillbox.airport.Terminal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

  }

  public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
    final int twoHours = 7_200_000;
    Date curTime = new Date(System.currentTimeMillis());
    Date twoHoursLater = new Date(System.currentTimeMillis() + twoHours);

    return airport.getTerminals().stream().flatMap(flight -> flight.getFlights().stream())
        .filter(flight -> flight.getType().equals(Type.DEPARTURE) && flight.getDate().after(curTime)
            && flight.getDate().before(twoHoursLater)).collect(Collectors.toList());
  }

}