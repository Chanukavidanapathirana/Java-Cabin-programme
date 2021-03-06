import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Cabin[] cabinRooms = new Cabin[Constants.CABINS_COUNT];//Cabin array objects
        Queue passengerQueue = new Queue();//queue object created
        crateFile();
        initialise(cabinRooms);
        menu(cabinRooms, passengerQueue);
    }

    /***
     * Initialising cabin rooms.
     * @param cabinRooms Array of cabins.
     */
    private static void initialise(Cabin cabinRooms[]) {

        for(int i = 0; i < Constants.CABINS_COUNT; i++){
            cabinRooms[i] = new Cabin();
            for(int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                cabinRooms[i].getPassengersInCabin()[j] = new Passenger();
            }
        }
        System.out.println("initialised");
    }

    public static void crateFile() {

        try {
            File cabinDetails = new File(Constants.File_Path);
            if (cabinDetails.createNewFile()) {
                System.out.println(String.format("File created: %S", cabinDetails.getName()));
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /***
     *Sorting passengers names alphabetically.
     * @param cabinRooms - cabins array.
     */
    public static void sortNames(Cabin[] cabinRooms) {

        int arrayLength = 0;
        for (int i = 0; i < cabinRooms.length; i++) {
            for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                if (!("empty".equals(cabinRooms[i].getPassengersInCabin()[j].getFullName()) || "e".equals(cabinRooms[i].getPassengersInCabin()[j].getSecondName()))) {
                    arrayLength++;
                }
            }
        }
        String[] sort = new String[arrayLength];
        //creating array(sort) only with names
        int count = 0;//using count to create sort array
        for (int i = 0; i < cabinRooms.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (!("empty".equals(cabinRooms[i].getPassengersInCabin()[j].getFullName()) || "empty".equals(cabinRooms[i].getPassengersInCabin()[j].getSecondName()))) {
                    sort[count] = cabinRooms[i].getPassengersInCabin()[j].getFullName();
                    count++;
                }
            }
        }
        //sorting starting
        int n = sort.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= n - 2; j++) {
                if ((sort[j]).compareTo(sort[j + 1]) > 0) {
                    String temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp;
                }
            }
        }
        System.out.println("* * * Sorted Array of passenger names * * *");
        System.out.println(Arrays.toString(sort));
    }

    /***
     * Programme main menu.
     * @param cabinRooms - cabin array.
     * @param passengerQueue - Passenger array for queue.
     */
    public static void menu(Cabin[] cabinRooms, Queue passengerQueue) {

        while (true) {
            System.out.println();
            System.out.println(" = = = = = Menu = = = = = ");
            System.out.println("Enter 'A' to add customer to the cabin");
            System.out.println("Enter 'V' to view all cabins");
            System.out.println("Enter 'E' to view empty cabins");
            System.out.println("Enter 'D' to delete a customer from cabin");
            System.out.println("Enter 'F' to find cabin from customer name");
            System.out.println("Enter 'S' to store program data into file ");
            System.out.println("Enter 'L' to load program data from file");
            System.out.println("Enter 'O' to  view passengers alphabetically ordered list");
            System.out.println("Enter 'T' to get expenses for a passenger and all the passengers total cost");
            System.out.println("Enter 'Q' to quite from the program");
            // Restart the while loop when entered a wrong input.
            String input = Utility.getUserInput("Enter  your input :- ");
                switch (input) {
                    case "A":
                        addPassengers(cabinRooms, passengerQueue);
                        break;
                    case "V":
                        for (int i = 0; i < Constants.CABINS_COUNT; i++) {
                            cabinRooms[i].viewCabin(i);
                        }
                        break;
                    case "E":
                        for (int i = 0; i < Constants.CABINS_COUNT;i++) {
                            if(cabinRooms[i].isCabinEmpty()){
                                System.out.println(String.format("Cabin no %s is empty",(i+1)));
                            }
                        }
                        break;
                    case "D":
                        System.out.println("Enter the cabin number to delete the passengers :- ");
                        Scanner input1 = new Scanner(System.in);
                        int i = input1.nextInt();
                        if (Utility.isCabinsFull(cabinRooms) || passengerQueue.isEmpty()) {
                            cabinRooms[i - 1].deletePassengers();
                        } else if (!(Utility.isCabinsFull(cabinRooms)) && !(passengerQueue.isEmpty())) {
                            cabinRooms[i - 1].dequeueAndDelete(passengerQueue);
                        }
                        break;
                    case "F":
                        String name =Utility.getUserInput("Enter passenger first name to find the cabin :- ");
                        for (int l = 0; l < Constants.CABINS_COUNT; l++) {
                            cabinRooms[l].findCabin(name, l);
                        }
                        break;
                    case "S":
                        storeCabinDetails(cabinRooms);
                        break;
                    case "L":
                        loadCabinDetails();
                        break;
                    case "O":
                        sortNames(cabinRooms);
                        break;
                    case "T":
                        cost(cabinRooms);
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("***Please enter a valid input***");

                }
                if ("Q".equals(input)) {
                    break;
                }

        }
    }

    /***
     * This method gives cost for one passenger and full cost for all passengers.
     * @param cabinRooms array cabin.
     */
    public static void cost(Cabin cabinRooms[]) {

        System.out.print("Enter the customer's first name to find the bill amount :-");
        Scanner inputForCost = new Scanner(System.in);
        String name = inputForCost.next();
        for (int i = 0; i < Constants.CABINS_COUNT; i++) {
            for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                if (cabinRooms[i].getPassengersInCabin()[j].getFirstName().equals(name)) {
                    System.out.println(String.format("%s (room no %s ) cost is %s", cabinRooms[i].getPassengersInCabin()[j].getFirstName(), (i + 1), cabinRooms[i].getPassengersInCabin()[j].getCostPerCustomer()));
                }
            }
        }
        System.out.println("* if you didn't get the cost according to the customer first name, please reenter the correct first name * ");

        double fullTotal = 0;
        for (Cabin cabinRoom : cabinRooms  ) {
            for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                fullTotal = fullTotal + cabinRoom.getPassengersInCabin()[j].getCostPerCustomer();
            }
        }
        System.out.println(String.format("Total bill of all passengers:- %s", fullTotal));
    }

    /***
     * Store cabin details to the folder.
     * @param cabinRooms cabin array.
     */
    public static void storeCabinDetails(Cabin[] cabinRooms) {

        try {
            FileWriter myWriter = new FileWriter("CabinDetails.txt");
            for (int i = 0; i < Constants.CABINS_COUNT; i++) {
                myWriter.write( "Cabin number "+ (i + 1) +System.lineSeparator());
                for (int j = 0; j < Constants.PASSENGERS_IN_A_CABIN; j++) {
                    myWriter.write(" passenger "+(j + 1)+" :- { " + cabinRooms[i].getPassengersInCabin()[j].getFullName() + " - " +cabinRooms[i].getPassengersInCabin()[j].getCostPerCustomer()+" }."+ System.lineSeparator());
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /***
     * Loading cabin data from file.
     */
    public static void loadCabinDetails() {

        try {
            File myObj = new File("CabinDetails.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /***
     * If cabins gets fill with passengers,programme will add them to queue.
     * @param cabinRooms - Cabin array.
     * @param queue - queue object.
     */
    public static void addPassengers(Cabin[] cabinRooms, Queue queue) {

        while (true) {
            if(!(Utility.isCabinsFull(cabinRooms))) {
                if (queue.isFull()) {
                    System.out.println("Queue is also filled.Sorry!");
                    break;
                }  else if ("Y".equals(Utility.askingAddingToQueue())) {
                    System.out.println("*Cabins full with passengers.We are going to add you to the queue.We will add you to a cabin when one of the passenger leave the cabin.");
                    System.out.println("");
                    queue.enQueue(Utility.enterFirstName(0), Utility.enterSurName(0), Utility.enterCostForCabin(0));
                }
            } else {
                int roomNum = Utility.askingCabinNo();
                if (roomNum == Constants.CABINS_COUNT + 1) {
                    break;
                } else if (roomNum >= 1 && roomNum < Constants.CABINS_COUNT + 1) {
                    System.out.println(String.format("****** Enter passengers names for room %s ****** ",roomNum));
                    //set customer1
                    cabinRooms[roomNum - 1].getPassengersInCabin()[0] = new Passenger(Utility.enterFirstName(0), Utility.enterSurName(0),Utility.enterCostForCabin(0));
                    for(int i = 1; i < Constants.PASSENGERS_IN_A_CABIN; i++) {
                        if ("Y".equals(Utility.askingForAdd(i + 1))) {
                            cabinRooms[roomNum - 1].getPassengersInCabin()[i] = new Passenger(Utility.enterFirstName(i), Utility.enterSurName(i), Utility.enterCostForCabin(i));
                        }
                    }
                } else {
                    System.out.println("* * * * * Please enter a valid input * * * * * ");
                }
            }
        }
    }

}
