
import java.util.Scanner;

public class Cabin {
    private Passenger [] GustsInCabin = new Passenger[Constants.PASSENGERS_IN_A_CABIN];

    public Passenger[] getGustsInCabin() {
        return GustsInCabin;
    }

    public void setGustsInCabin(Passenger[] gustsInCabin) {
        this.GustsInCabin = gustsInCabin;
    }

    public void viewCabin(int i) {
        if ("empty".equals(getGustsInCabin()[0].getFirstName()) && "empty".equals(getGustsInCabin()[1].getFirstName()) && "empty".equals(getGustsInCabin()[2].getFirstName())) {
            System.out.println("cabin " + (i + 1) + " is empty");
        } else {
            System.out.println(String.format("Cabin %s occupied by %s , %s , %s",(i + 1),getGustsInCabin()[0].getFullName(),getGustsInCabin()[1].getFullName() , getGustsInCabin()[2].getFullName()));
        }
    }

    public boolean isCabinEmpty() {
    boolean check = "empty".equals(getGustsInCabin()[0].getFirstName()) && "empty".equals(getGustsInCabin()[1].getFirstName()) && "empty".equals(getGustsInCabin()[2].getFirstName());
    return check;
    }

    public void findCabin(String name, int i) {
        for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN ; j++) {
            if (getGustsInCabin()[j].getFirstName().equals(name))
                System.out.println(String.format("%s is in cabin number %s",getGustsInCabin()[j].getFirstName(),(i+1)));
        }
    }

    public void deletePassengers() {
        for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
            getGustsInCabin()[j] = new Passenger();
        }
    }

    public void dequeueAndDelete(Queue passengerQueue) {
        //deque
       getGustsInCabin()[0].setFirstName(passengerQueue.deQueue().getFirstName());
       getGustsInCabin()[0].setSecondName(passengerQueue.deQueue().getSecondName());
       getGustsInCabin()[0].setFullName(passengerQueue.deQueue().getFirstName(), passengerQueue.deQueue().getSecondName());
       getGustsInCabin()[0].setCostPerCustomer(passengerQueue.deQueue().getCostPerCustomer());
       for(int k = 1; k < Constants.PASSENGERS_IN_A_CABIN; k++) {
           getGustsInCabin()[k] = new Passenger();
       }
        passengerQueue.updatingDequeue();
    }
}
