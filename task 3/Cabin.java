/***
 * This is class made for cabin.
 */
public class Cabin {

    private Passenger [] passengersInCabin = new Passenger[Constants.PASSENGERS_IN_A_CABIN];

    public Passenger[] getPassengersInCabin() {
        return passengersInCabin;
    }

    public void setPassengersInCabin(Passenger[] passengersInCabin) {
        this.passengersInCabin = passengersInCabin;
    }

    public void viewCabin(int i) {

        if (isCabinEmpty()) {
            System.out.println("cabin " + (i + 1) + " is empty");
        } else {
            System.out.println(String.format("Cabin %s occupied by %s , %s , %s",(i + 1), getPassengersInCabin()[0].getFullName(), getPassengersInCabin()[1].getFullName() , getPassengersInCabin()[2].getFullName()));
        }
    }

    public boolean isCabinEmpty() {

        boolean check = true;
        for(int i  = 0; i < Constants.PASSENGERS_IN_A_CABIN; i++){
           check = check && "empty".equals(getPassengersInCabin()[0].getFirstName());
           if (check == false){
               return false;
           }
        }
        return true;
    }

    public void findCabin(String name, int i) {

        //todo this is  not suatiable for this class.*****
        for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN ; j++) {
            if (getPassengersInCabin()[j].getFirstName().equals(name))
                System.out.println(String.format("%s is in cabin number %s",
                        getPassengersInCabin()[j].getFirstName(),(i+1)));
        }
    }

    public void deletePassengers() {
        for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
            getPassengersInCabin()[j] = new Passenger();
        }
    }

    public void dequeueAndDelete(Queue passengerQueue) {

        //deque
       getPassengersInCabin()[0].setFirstName(passengerQueue.deQueue().getFirstName());
       getPassengersInCabin()[0].setSecondName(passengerQueue.deQueue().getSecondName());
       getPassengersInCabin()[0].setFullName(passengerQueue.deQueue().getFirstName(), passengerQueue.deQueue().getSecondName());
       getPassengersInCabin()[0].setCostPerCustomer(passengerQueue.deQueue().getCostPerCustomer());
       for(int k = 1; k < Constants.PASSENGERS_IN_A_CABIN; k++) {
           getPassengersInCabin()[k] = new Passenger();
       }
        passengerQueue.updatingDequeue();
    }
}
