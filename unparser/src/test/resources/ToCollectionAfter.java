package test.resources;

import java.util.List;

public class RoadTrip {

    void start(List<Car> automobiles) {
        for (Car automobile : automobiles) {
            automobile.start();
        }
    }
}
