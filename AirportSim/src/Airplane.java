public class Airplane { // we define the Plane class
    private static int idCounter = 0;
    private int id;
    private boolean isEmergency;

    public Airplane(boolean isEmergency) { // we make a constructor to initialize airplane with a unique ID and emergency status
        this.id=++idCounter;
        this.isEmergency=isEmergency;
    }

    // we use a getter method to get airplane ID
    public int getId() {
        return id;
    }

    // we use a getter method to check if the airplane has an emergency status
    public boolean isEmergency() {
        return isEmergency;
    }

    @Override
    public String toString() { // we use override toString() method to have a readable status of the airplane
        return "Flight " + id;
    }
}