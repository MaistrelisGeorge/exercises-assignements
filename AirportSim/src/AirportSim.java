import java.util.Random;
public class AirportSim { //we define the AirportSim class
    public static void main(String[] args) throws InterruptedException {
        Airport airport=new Airport(); // we create an instance of the Airport class
        Random random=new Random(); // we create an instance of the Random class

        // we create a for sim loop
        for (int i=0; i<10; i++) { // 10 actions
            Thread.sleep(1000); // delay between actions

            // we generate a random action and create an airplane
            int action = random.nextInt(3);
            Airplane airplane = new Airplane(random.nextBoolean()); // set emergency status at random

            // we choose an action based on the value generated at random
            switch (action) {
                case 0:
                    System.out.println(airplane + " requests landing"); // landing request
                    airport.requestLanding(airplane); // add landing request to the airport
                    break;
                case 1:
                    System.out.println(airplane + " requests emergency landing"); // emergency landing request
                    airport.requestLanding(airplane); // add emergency landing request to the airport
                    break;
                case 2:
                    System.out.println(airplane + " requests takeoff"); // takeoff request
                    airport.requestTakeoff(airplane); // add takeoff request to the airport
                    break;
            }
            // choose action based on priority
            Airplane emergencyAirplane = airport.getNextEmergencyLanding(); // Retrieve airplane for emergency landing
            if (emergencyAirplane != null) {
                System.out.println("CONTROL: " + emergencyAirplane + " land (EMERGENCY)"); // Control emergency landing
            } else {
                Airplane landingAirplane = airport.getNextLanding(); // Retrieve airplane for normal landing
                if (landingAirplane != null) {
                    System.out.println("CONTROL: " + landingAirplane + " land"); // Control normal landing
                } else {
                    Airplane takeoffAirplane = airport.getNextTakeoff(); // Retrieve airplane for takeoff
                    if (takeoffAirplane != null) {
                        System.out.println("CONTROL: " + takeoffAirplane + " takeoff"); // Control takeoff
                    }
                }
            }
        }
    }
}
