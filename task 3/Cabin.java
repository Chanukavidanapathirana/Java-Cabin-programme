
import java.util.Scanner;

public class Cabin {

    private Passenger [] GustsInCabin = new Passenger[Constants.PASSENGERS_IN_A_CABIN];


//getters
    public Passenger[] getGustsInCabin() {
        return GustsInCabin;
    }
//setters(*****check it is worthy or not)
    public void setGustsInCabin(Passenger[] gustsInCabin) {
        this.GustsInCabin = gustsInCabin;
    }

    //Deleting
    public void deletePassengersFromCabin(Queue queue, Cabin[] cabinRooms) {
        if (Main.cabinFullOrNot(cabinRooms) || queue.isEmpty()) {
            for (int i = 0; i < Constants.PASSENGERS_IN_A_CABIN; i++) {
                getGustsInCabin()[i] = new Passenger();
            }
        } else if (!(Main.cabinFullOrNot(cabinRooms)) && !(queue.isEmpty())) {
            //deque
            getGustsInCabin()[0].setFirstName(queue.deQueue().getFirstName());
            getGustsInCabin()[0].setSecondName(queue.deQueue().getSecondName());
            getGustsInCabin()[0].setFullName(queue.deQueue().getFirstName(), queue.deQueue().getSecondName());
            getGustsInCabin()[0].setCostPerCustomer(queue.deQueue().getCostPerCustomer());
            //second passenger
            getGustsInCabin()[1] = new Passenger();
            //third passenger
            getGustsInCabin()[2] = new Passenger();
            queue.updatingDequeue();
        }
    }

    /***
     * This method is use for add passengers to cabins or queue.
     * @param cabinRooms - Cabin array.
     * @param queue - queue object.
     */
    public static void addPassengers(Cabin[] cabinRooms, Queue queue) {
        //creating strings for take names
        String passenger1Firstname;
        String passenger1Surname;
        double passenger1Cost;
        String passenger2Firstname;
        String passenger2Surname;
        double passenger2Cost;
        String passenger3Firstname;
        String passenger3Surname;
        double passenger3Cost;
        int roomNum = 0;
        Scanner input = new Scanner(System.in);
        while (true) {
            if(!(Main.cabinFullOrNot(cabinRooms))){//check the cabins
                System.out.print("If you want to add another passenger to queue, press 'y'. If not, press any key:- ");
                String addQorNot = input.next();
                addQorNot = addQorNot.toUpperCase();
                if (queue.isFull()) {
                    System.out.println("Queue is also filled.Sorry!");
                    break;
                }  else if (addQorNot.equals("Y")) {
                    System.out.println("*Cabins full with passengers.We are going to add you to the queue.We will add you to a cabin when one of the passenger leave the cabin");
                    System.out.println("");
                    System.out.print("Passenger first name:- ");
                    passenger1Firstname = input.next();
                    System.out.print("Passenger surname:- ");
                    passenger1Surname = input.next();
                    System.out.print("Passenger cost:- ");
                    passenger1Cost = input.nextDouble();
                    queue.enQueue(passenger1Firstname, passenger1Surname, passenger1Cost);
                } else {
                    break;
                }
            } else {
                System.out.println("Enter cabin number (1-12) or 13 to stop:");
                roomNum = input.nextInt();
                //break the iteration
                if (roomNum == 13) {
                    break;
                } else if (roomNum >= 1 && roomNum < 13) {
                    System.out.println(String.format("****** Enter passengers names for room %s ****** ",roomNum));
                    //passenger 1 details
                    System.out.print("Passenger 1 first name :- ");
                    passenger1Firstname = input.next();
                    System.out.print("passenger 1 surname:- ");
                    passenger1Surname = input.next();
                    System.out.print("Enter the passenger 1 bill amount:-");
                    passenger1Cost = input.nextDouble();
                    //set customer1
                    cabinRooms[roomNum - 1].getGustsInCabin()[0].setFirstName(passenger1Firstname);
                    cabinRooms[roomNum - 1].getGustsInCabin()[0].setSecondName(passenger1Surname);
                    cabinRooms[roomNum - 1].getGustsInCabin()[0].setFullName(passenger1Firstname, passenger1Surname);
                    cabinRooms[roomNum - 1].getGustsInCabin()[0].setCostPerCustomer(passenger1Cost);
                    //asking to add another passenger
                    System.out.print("If you want to add another passenger, press 'y'. If not, press any key(Passenger 2) :- ");
                    String addOrNot = input.next();
                    addOrNot = addOrNot.toUpperCase();
                    if (addOrNot.equals("Y")) {
                        //passenger 2 details
                        System.out.print("Passenger 2 first name :- ");
                        passenger2Firstname = input.next();
                        System.out.print("passenger 2 surname:- ");
                        passenger2Surname = input.next();
                        System.out.print("Enter the passenger 2 bill amount:-");
                        passenger2Cost = input.nextDouble();
                        //set customer2
                        cabinRooms[roomNum - 1].getGustsInCabin()[1].setFirstName(passenger2Firstname);
                        cabinRooms[roomNum - 1].getGustsInCabin()[1].setSecondName(passenger2Surname);
                        cabinRooms[roomNum - 1].getGustsInCabin()[1].setFullName(passenger2Firstname, passenger2Surname);
                        cabinRooms[roomNum - 1].getGustsInCabin()[1].setCostPerCustomer(passenger2Cost);
                    }
                    System.out.print("If you want to add another passenger, press 'y'. If not, press any key(Passenger 3) :- ");
                    String addOrNot1 = input.next();
                    addOrNot1 = addOrNot1.toUpperCase();
                    if (addOrNot1.equals("Y")) {
                        //passenger 3 details
                        System.out.print("Passenger 3 first name :- ");
                        passenger3Firstname = input.next();
                        System.out.print("passenger 3 surname:- ");
                        passenger3Surname = input.next();
                        System.out.print("Enter the passenger 3 bill amount:-");
                        passenger3Cost = input.nextDouble();
                        //set customer3
                        cabinRooms[roomNum - 1].getGustsInCabin()[2].setFirstName(passenger3Firstname);
                        cabinRooms[roomNum - 1].getGustsInCabin()[2].setSecondName(passenger3Surname);
                        cabinRooms[roomNum - 1].getGustsInCabin()[2].setFullName(passenger3Firstname, passenger3Surname);
                        cabinRooms[roomNum - 1].getGustsInCabin()[2].setCostPerCustomer(passenger3Cost);
                    }
                } else {
                    System.out.println("* * * * * Please enter a valid input * * * * * ");
                }
            }
        }
    }

    public void viewCabin(int i) {
        //Todo- empty word should put out of the  equals
        if (getGustsInCabin()[0].getFirstName().equals("empty") && getGustsInCabin()[1].getFirstName().equals("empty") && getGustsInCabin()[2].getFirstName().equals("empty")) {
            System.out.println("cabin " + (i + 1) + " is empty");
        } else {
            System.out.println(String.format("Cabin %s occupied by %s , %s , %s",(i + 1),getGustsInCabin()[0].getFullName(),getGustsInCabin()[1].getFullName() , getGustsInCabin()[2].getFullName()));
        }
    }

    public void isCabinEmpty(int i) {
        if (getGustsInCabin()[0].getFirstName().equals("empty") && getGustsInCabin()[1].getFirstName().equals("empty") && getGustsInCabin()[2].getFirstName().equals("empty")) {
            System.out.println("cabin " + (i + 1) + " is empty");
        }
    }

    public void findCabin(String name, int i) {
        for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN ; j++) {
            if (getGustsInCabin()[j].getFirstName().equals(name))
                System.out.println(String.format("%s is in cabin number %s",getGustsInCabin()[j].getFirstName(),(i+1)));
        }
    }
}
