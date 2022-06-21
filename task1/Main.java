import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int NUMBER_OF_CABINS = 12 ;


    public static void main(String[] args){
        int[] cabinNumber = new int[Main.NUMBER_OF_CABINS];
        String[] passengerName = new String[Main.NUMBER_OF_CABINS];
        initialise(cabinNumber, passengerName);

        //creating a file to store information about cabins
        File cabinFile = null;
        try {
            cabinFile = new File("Cabin Details.txt");
            if (cabinFile.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        menu(passengerName,cabinNumber, cabinFile);



    }

    /***
     * Making arrays for cabins, passengers and creating file.
     * @param cabinNo - creating 1 to 12 numbers for cabins.
     * @param name - Cabin owner name.
     */
    private static void initialise(int[] cabinNo,String[] name){
        System.out.println("---Cabin reservation system---");
        for(int i=0; i<12;i++) {
            cabinNo[i] = i + 1;
            name[i]="Empty";
            System.out.println("Cabin " + cabinNo[i] + " is " + name[i]);
        }

    }

    /***
     * menu is in while true loop. User have to press 'Q' to quite
     * @param passengerName - passenger name list.
     * @param cabinNumber - cabin numbers.
     * @param cabinFile - File object name.
     */
    public static void menu(String[] passengerName, int[] cabinNumber, File cabinFile ){
        //Menu list

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
            System.out.println("Enter 'Q' to quite from the program");
            System.out.println("Enter  your input :- ");
            Scanner userInput = new Scanner(System.in);
            String input = userInput.next();
            input.toUpperCase();
            if(checkInput(input)){
                System.out.println("***Please enter a valid input***");
                continue;
            }else{
                switch (input){
                    case "A":
                        Add(passengerName);
                        break;
                    case "V":
                        View(cabinNumber, passengerName);
                        break;
                    case "E":
                        viewEmptyCabins(cabinNumber, passengerName);
                        break;
                    case "D":
                        deleteCustomer(passengerName);
                        break;
                    case "F":
                        findCabinNo(passengerName);
                        break;
                    case "S":
                        storeData(passengerName);
                        break;
                    case "L":
                        loadData(cabinFile,passengerName);
                        break;
                    case "O":
                        passengerSort(passengerName);
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
     * to check whether the <b>entered input</b> is correct.
     * @param input - user entered String value.
     * @return - if user entered wrong input this return true
     */
    public static boolean checkInput(String input) {
        return (!("A".equals(input)||"V".equals(input) ||"E".equals(input)||"D".equals(input)||"F".equals(input)||"S".equals(input)||"L".equals(input)||"O".equals(input)||"Q".equals(input)));
    }


    /***
     * Method to view all cabins.
     * @param cabinNo cabin numbers.
     * @param name passengers name.
     */
    private static void View(int[] cabinNo,String[] name) {
        System.out.println("-----Details about all rooms-----\n");
        for (int i = 0 ; i < 12 ; i++) {
            if ("Empty".equals(name[i])) {
                System.out.println("Cabin " + cabinNo[i] + " is " + name[i]);
            } else {
                System.out.println("Cabin " + cabinNo[i] + " is owned by " + name[i]);
            }
        }

    }

    /***
     * Method to display all empty cabins.
     * @param cabinNo cabin numbers
     * @param name passengers name.
     */
    public static void viewEmptyCabins(int[] cabinNo,String[] name){
        System.out.println("-----Empty rooms-----\n");
        for (int i = 0 ; i < 12 ; i++) {
            if (name[i].equals("Empty")) {
                System.out.println("Cabin " + cabinNo[i] + " is " + name[i]);
            }
        }

    }

    /***
     * method to delete customer from cabin.
     * @param name passenger name.
     */
    public static void deleteCustomer(String[] name){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter the cabin number to delete the customer: ");
        int roomNumber=input.nextInt();
        name[roomNumber-1]="Empty.";
        System.out.println("Customer deleted successfully.");

    }

    /***
     * Method to add customer for cabins.
     * @param name passenger name.
     */
    public static void Add(String[] name ){
        while(true) {
            Scanner adding = new Scanner (System.in);
            System.out.println("Please enter the cabin no(1-12) : ");
            int cabinNo = adding.nextInt();
            if (cabinNo > 13 || cabinNo < 1) {//for end the loop
                System.out.println("Please enter a valid input.");//if the user input a wrong number
                continue;


            } else if  (cabinNo <= 12 && cabinNo >= 1) {
                System.out.println("Please enter the customer name: ");
                String cusName = adding.next();


                name[cabinNo - 1] = cusName;
                System.out.println();
                break;
            } else {
                System.out.println("Please enter a valid input");
                continue;

            }

        }

    }

    /***
     * Method to find customer's cabin number.
     * @param names passengers names.
     */
    public static  void findCabinNo(String[] names){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the customer name: ");
        String customerName = input.next();
        for(int i=0;i<names.length;i++){
            if(names[i].equals(customerName)){
                System.out.println( customerName +" is in cabin Number "+ (i+1));

            }
        }

    }

    /***
     * This method is use for store data to file.
     * @param name passengers names.
     */
    public static void storeData(String[] name) {
        try {
            FileWriter writer = new FileWriter("Cabin Details.txt");
            for (String str : name) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
            System.out.println("Successfully wrote to the file. ");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /***
     * This method is use for read data in file.
     * @param filename created file name.
     * @param name passenger name.
     */
    public static void loadData(File filename,String[] name){
        try{
            int i = 0;
            Scanner myReader = new Scanner(filename);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
                name[i] = data;
                i++;
            }
            myReader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /***
     * this method use for sort passenger's name alphabetically.
     * bubble sort
     * @param name passengers names
     */
    public static void passengerSort(String[] name){
        //counting the new array(sort) length
        int arraylength = 0;
        for(int i =0;i<name.length;i++){
            if(name[i].equals("Empty")){

            }else{
                arraylength++;
            }

        }
        String[] sort = new String[arraylength];
        //creating array(sort) only with names
        int count=0;//using count to create sort array
        for(int i =0;i<name.length;i++){
            if(name[i].equals("Empty")){

            }else{
                sort[count]= name[i];
                count++;
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


        System.out.println(Arrays.toString(sort));

    }
}
