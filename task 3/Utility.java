import java.util.Scanner;

public class Utility {

    public static boolean AreCabinsFull(Cabin[] cabinRooms) {
        boolean check = false;
        for (int i = 0; i < 12; i++) {
            check = check || cabinRooms[i].isCabinEmpty();
        }
        return check;
    }

    public static String askingAddingToQueue() {
        Scanner input = new Scanner(System.in);
        System.out.print("If you want to add another passenger to queue, press 'y'. If not, press any key:- ");
        String addQorNot = input.next();
        addQorNot = addQorNot.toUpperCase();
        return addQorNot;
    }

    public static String enterFirstName(int i){
        Scanner input = new Scanner(System.in);
        if (i > 0) {
            System.out.print(String.format("Passenger %s first name:- ", i + 1));
            String passenger1Firstname = input.next();
            return passenger1Firstname;
        } else {
            System.out.print("Passenger first name:- ");
            String passenger1Firstname = input.next();
            return passenger1Firstname;
        }
    }

    public static String enterSurName(int i){
        Scanner input = new Scanner(System.in);
        if (i > 0) {
            System.out.print(String.format("Passenger %s surname:- ", i));
            String passenger1surname = input.next();
            return passenger1surname;
        } else {
            System.out.print("Passenger surname:- ");
            String passenger1surname = input.next();
            return passenger1surname;
        }
    }

    public static double enterCostForCabin(int i) {
        Scanner input = new Scanner(System.in);
        if (i > 0) {
            System.out.print(String.format("Passenger %s cost:- ", i));
            double passengerCost = input.nextDouble();
            return passengerCost;
        } else {
            System.out.print("Passenger cost:- ");
            double passengerCost = input.nextDouble();
            return passengerCost;
        }
    }

    public static int askingCabinNo() {
        Scanner input = new Scanner(System.in);
        System.out.println(String.format("Enter cabin number (1-%s) or %s to stop:",Constants.CABINS_COUNT,Constants.CABINS_COUNT + 1));
        int roomNum = input.nextInt();
        return roomNum;
    }
    public static String askingForAdd(int i) {
        Scanner input = new Scanner(System.in);
        System.out.print(String.format("If you want to add another passenger, press 'y'. If not, press any key(Passenger %s) :- ", i));
        String addOrNot = input.next();
        addOrNot = addOrNot.toUpperCase();
        return addOrNot;
    }

}
