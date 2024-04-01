import java.util.LinkedList;
import java.util.Queue;
public class Airport { //we define the airport class
    // we create queues for landing, emergency landing, and takeoff requests
    private Queue<Airplane> landingRequests;
    private Queue<Airplane> emergencyRequests;
    private Queue<Airplane> takeoffRequests;

    // we make a constructor to initialize queues
    public Airport() {
        landingRequests=new LinkedList<>();
        emergencyRequests=new LinkedList<>();
        takeoffRequests=new LinkedList<>();
    }
    public void requestLanding(Airplane airplane) { //we make a method to add landing requests to the appropriate queue
        if (airplane.isEmergency()) {
            emergencyRequests.add(airplane);
        } else {
            landingRequests.add(airplane);
        }
    }
    public void requestTakeoff(Airplane airplane) { // we make a method to add takeoff requests to the takeoff queue
        takeoffRequests.add(airplane);
    }
    public Airplane getNextLanding() { // we make a method to retrieve the next airplane for landing
        return landingRequests.poll();
    }
    public Airplane getNextEmergencyLanding() { // we make a method to retrieve the next airplane for emergency landing
        return emergencyRequests.poll();
    }
    public Airplane getNextTakeoff() { // we maek a method to retrieve the next airplane for takeoff
        return takeoffRequests.poll();
    }
}
