package test.resources;

public class MoveStatement {
    int drive(Car car) {
        int status;
        if (!car.isStarted()) {
            car.start();
        }
        car.accelerate();
        status = car.getGasPercentage();
        return status;
    }
}
