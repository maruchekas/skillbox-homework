import core.Line;
import core.Station;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;

public class RouteCalculatorTest extends TestCase {

  List<Station> route;
  StationIndex stationIndex = new StationIndex();
  RouteCalculator routeCalculator = new RouteCalculator(stationIndex);

  @Override
  protected void setUp() {

    route = new ArrayList<>();
    Line line1 = new Line(1, "First");
    Line line2 = new Line(2, "Second");
    Line line3 = new Line(3, "Third");

    stationIndex.addLine(line1);
    stationIndex.addLine(line2);
    stationIndex.addLine(line3);

    stationIndex.addStation(new Station("Чкаловская", line1));
    stationIndex.addStation(new Station("Спортивная", line1));
    stationIndex.addStation(new Station("Адмиралтейская", line1));
    stationIndex.addStation(new Station("Садовая", line1));
    stationIndex.addStation(new Station("Звенигородская", line1));
    stationIndex.addStation(new Station("Спасская", line2));
    stationIndex.addStation(new Station("Достоевская", line2));
    stationIndex.addStation(new Station("Лиговский проспект", line2));
    stationIndex.addStation(new Station("Владимирская", line3));
    stationIndex.addStation(new Station("Пушкинская", line3));

    line1.addStation(stationIndex.getStation("Чкаловская"));
    line1.addStation(stationIndex.getStation("Спортивная"));
    line1.addStation(stationIndex.getStation("Адмиралтейская"));
    line1.addStation(stationIndex.getStation("Садовая"));
    line1.addStation(stationIndex.getStation("Звенигородская"));
    line2.addStation(stationIndex.getStation("Спасская"));
    line2.addStation(stationIndex.getStation("Достоевская"));
    line2.addStation(stationIndex.getStation("Лиговский проспект"));
    line3.addStation(stationIndex.getStation("Владимирская"));
    line3.addStation(stationIndex.getStation("Пушкинская"));

    List<Station> con1 = Arrays
        .asList(stationIndex.getStation("Садовая", 1),
            stationIndex.getStation("Спасская", 2));
    List<Station> con2 = Arrays
        .asList(stationIndex.getStation("Достоевская", 2),
            stationIndex.getStation("Владимирская", 3));

    stationIndex.addConnection(con1);
    stationIndex.addConnection(con2);

    route.add(stationIndex.getStation("Адмиралтейская"));
    route.add(stationIndex.getStation("Садовая"));
    route.add(stationIndex.getStation("Спасская"));
    route.add(stationIndex.getStation("Достоевская"));
    route.add(stationIndex.getStation("Лиговский проспект"));
    route.add(stationIndex.getStation("Владимирская"));
    route.add(stationIndex.getStation("Пушкинская"));
  }

  @Test
  public void testCalculateDuration() {
    double actual = RouteCalculator.calculateDuration(route);
    double expected = 17;
    assertEquals(expected, actual);
  }

  @Test
  public void testShortestRouteSize() {
    List<Station> actual = routeCalculator
        .getShortestRoute(stationIndex.getStation("Чкаловская"),
            stationIndex.getStation("Лиговский проспект"));

    int actualRouteSize = actual.size();
    int expectedRouteSize = 7;

    assertEquals(expectedRouteSize, actualRouteSize);
  }

  @Test
  public void testGetRouteOneTheLine() {
    List<Station> expected = Arrays
        .asList(stationIndex.getStation("Чкаловская"),
            stationIndex.getStation("Спортивная"),
            stationIndex.getStation("Адмиралтейская"),
            stationIndex.getStation("Садовая"),
            stationIndex.getStation("Звенигородская"));

    List<Station> actual = routeCalculator
        .getShortestRoute(stationIndex.getStation("Чкаловская"),
            stationIndex.getStation("Звенигородская"));
    assertEquals(expected, actual);
  }

  @Test
  public void testGetRouteWithOneConnection() {
    List<Station> expected = Arrays
        .asList(stationIndex.getStation("Чкаловская"),
            stationIndex.getStation("Спортивная"),
            stationIndex.getStation("Адмиралтейская"),
            stationIndex.getStation("Садовая"),
            stationIndex.getStation("Спасская"),
            stationIndex.getStation("Достоевская"),
            stationIndex.getStation("Лиговский проспект"));

    List<Station> actual = routeCalculator
        .getShortestRoute(stationIndex.getStation("Чкаловская"),
            stationIndex.getStation("Лиговский проспект"));
    assertEquals(expected, actual);
  }

  @Test
  public void testGetRouteWithTwoConnection() {
    List<Station> expected = Arrays
        .asList(stationIndex.getStation("Чкаловская"),
            stationIndex.getStation("Спортивная"),
            stationIndex.getStation("Адмиралтейская"),
            stationIndex.getStation("Садовая"),
            stationIndex.getStation("Спасская"),
            stationIndex.getStation("Достоевская"),
            stationIndex.getStation("Владимирская"),
            stationIndex.getStation("Пушкинская"));

    List<Station> actual = routeCalculator
        .getShortestRoute(stationIndex.getStation("Чкаловская"),
            stationIndex.getStation("Пушкинская"));
    assertEquals(expected, actual);
  }
}