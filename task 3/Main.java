import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Cabin[] cabinRooms = new Cabin[CONSTANTS.CABINS_COUNT];//Cabin array objects
        Queue passengerQueue = new Queue();//queue object created
        crateFile();
        initialise(cabinRooms);
        menu(cabinRooms,passengerQueue);



    }

    /***
     * initialising cabin rooms.
     * @param cabinRooms Array of cabins.
     */
    private static void initialise(Cabin cabinRooms[]) {
        for(int i =0; i < cabinRooms.length; i++  ){
            cabinRooms[i] = new Cabin();



            for(int j = 0 ; j < CONSTANTS.PASSENGERS_IN_A_CABIN; j++ ) {
                cabinRooms[i].getGustsInCabin()[j] = new Passenger();


            }

        }



        System.out.println("initialised");
    }

    /***
     * Creating a file to store data
     */
    public static void crateFile(){

        try {
            File cabinDetails = new File("CabinDetails.txt");
            if (cabinDetails.createNewFile()) {
                System.out.println(String.format("File created: %S",cabinDetails.getName()) );
            } else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /***
     *Sorting passengers names alphabetically.
     * @param cabinRooms - cabins array.
     */
    public static void sortNames(Cabin[] cabinRooms){
        int arrayLength = 0;
        for(int i =0;i<cabinRooms.length;i++) {
            for (int j = 0; j < CONSTANTS.PASSENGERS_IN_A_CABIN; j++){
                if ("empty".equals(cabinRooms[i].getGustsInCabin()[j].getFullName())||"e".equals(cabinRooms[i].getGustsInCabin()[j].getSecondName())) {

                } else {
                    arrayLength++;
                }
            }
        }
        String[] sort = new String[arrayLength];
        //creating array(sort) only with names
        int count=0;//using count to create sort array
        for(int i =0;i<cabinRooms.length;i++){
            for(int j = 0 ; j<3 ;j++) {
                if ("empty".equals(cabinRooms[i].getGustsInCabin()[j].getFullName())||"empty".equals(cabinRooms[i].getGustsInCabin()[j].getSecondName())) {

                } else {
                    sort[count] = cabinRooms[i].getGustsInCabin()[j].getFullName();
                    count++;
                }
            }
        }
        //sorting starting
        int n = sort.length;
        for (int i =0 ; i<n-1 ; i++){
            for (int j = 0 ; j<=n-2 ; j++){
                if((sort[j]).compareTo(sort [j+1])>0){
                    // swap arr[j+1] and arr[j]
                    String temp =sort[j];
                    sort[j] = sort[j+1];
                    sort[j+1] =temp;

                }
            }

        }

        System.out.println("* * * Sorted Array of passenger names * * *");
        System.out.println(Arrays.toString(sort));

    }

    /***
     * Checking whether cabins full or not.
     * @param cabinRooms - cabin array.
     * @return
     */
    public static boolean cabinFullOrNot(Cabin[] cabinRooms){
        boolean check = false;
        for(int i = 0; i<12; i++) {
            check = check || "empty".equals(cabinRooms[i].getGustsInCabin()[0].getFullName());
        }
        return check;
    }

    /***
     * programme main menu.
     * @param cabinRooms - cabin array.
     * @param passengerQueue - Passenger array for queue.
     */
    public static void menu(Cabin[] cabinRooms,Queue passengerQueue){
        while(true){
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

            System.out.println("Enter  your input :- ");
            Scanner userInput = new Scanner(System.in);
            String input = userInput.next();
            input = input.toUpperCase();
            // restart the while loop when entered a wrong input.
            if(!(input.equals("A")||input.equals("V") ||input.equals("E")||input.equals("D")||input.equals("F")||input.equals("S")||input.equals("L")||input.equals("O")||input.equals("Q")||input.equals("T"))){
                System.out.println("***Please enter a valid input***");
                continue;
            }else{
                switch (input){
                    case "A":
                        Cabin.addPassengers(cabinRooms,passengerQueue);
                        break;
                    case "V":
                        for (int i =0 ; i<CONSTANTS.CABINS_COUNT ; i++){
                            cabinRooms[i].viewCabin(i);
                        }

                        break;
                    case "E":
                        for(int i = 0 ; i < CONSTANTS.CABINS_COUNT ;i++) {
                            cabinRooms[i].isCabinEmpty(i);
                        }
                        break;
                    case "D":
                        System.out.println("Enter the cabin number to delete the passengers :- ");
                        Scanner input1 = new Scanner(System.in);
                        int i = input1.nextInt();
                        cabinRooms[i-1].deletePassengersFromCabin(passengerQueue,cabinRooms);
                        break;
                    case "F":
                        Scanner inputFind = new Scanner(System.in);
                        System.out.print("Enter passenger first name to find the cabin :- ");
                        String name = inputFind.next();

                        for(int l =0 ; l < CONSTANTS.CABINS_COUNT ;l++){

                            cabinRooms[l].findCabin(name,l);}
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
                }
                if(input.equals("Q")){
                    break;
                }
            }

        }
    }

    /***
     * This method gives cost for one passenger and full cost for all passengers
     * @param cabinRooms array cabin.
     */
    public static void cost(Cabin cabinRooms[]){
        System.out.print("Enter the customer's first name to find the bill amount :-");
        Scanner inputForCost= new Scanner(System.in);
        String name = inputForCost.next();
        for(int i = 0; i<12 ;i++){
            for (int j = 0 ;j<3 ;j++) {
                if(cabinRooms[i].getGustsInCabin()[j].getFirstName().equals(name))
                    System.out.println(String.format("%s (room no %s ) cost is %s",cabinRooms[i].getGustsInCabin()[j].getFirstName(),(i+1),cabinRooms[i].getGustsInCabin()[j].getCostPerCustomer()));
            }
        }
        System.out.println("* if you didn't get the cost according to the customer first name, please reenter the correct first name * ");

        double fullTotal = 0;
        for(int i = 0; i<12 ;i++){
            for (int j = 0 ;j<3 ;j++) {
                fullTotal=fullTotal+ cabinRooms[i].getGustsInCabin()[j].getCostPerCustomer();
            }
        }
        System.out.println(String.format("Total bill of all passengers:- %s",fullTotal));
    }

    /***
     * Store cabin details to the folder.
     * @param cabinRooms cabin array.
     */
    public static void storeCabinDetails(Cabin[] cabinRooms){
        try {

            FileWriter myWriter = new FileWriter("CabinDetails.txt");
            for (int i = 0; i<12 ; i++) {
                myWriter.write( "Cabin number "+ (i+1) +System.lineSeparator());
                for(int j = 0; j<3 ; j++) {
                    myWriter.write(" passenger "+(j+1)+" :- { " + cabinRooms[i].getGustsInCabin()[j].getFullName() + " - " +cabinRooms[i].getGustsInCabin()[j].getCostPerCustomer()+" }"+ System.lineSeparator());
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
    public static void loadCabinDetails(){
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

}