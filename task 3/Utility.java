import java.util.Scanner;

/***
 * Utility class.
 */
public class Utility {

    public static String getUserInput(String banner) {

        System.out.println(banner);
        Scanner input = new Scanner(System.in);
        return input.next();
    }

   public static double getUserDoubleInput(String banner){
        System.out.println(banner);
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }

    public static boolean isCabinsFull(Cabin[] cabinRooms) {

        boolean check = false;
        for (int i = 0; i < Constants.CABINS_COUNT; i++) {
            check = check || cabinRooms[i].isCabinEmpty();
            if (check == true){
                return true;
            }
        }
        return false;
    }

    public static String askingAddingToQueue() {
        String addQorNot = getUserInput("If you want to add another passenger to queue, press 'y'. If not, press any key:- ").toUpperCase();
        return addQorNot;
    }

    public static String enterFirstName(int i) {

        if (i > 0) {
            return getUserInput(String.format("Passenger %s first name:- ", i + 1));
        } else {
            return getUserInput("Passenger first name:- ");
        }
    }

    public static String enterSurName(int i) {

        if (i > 0) {
            return getUserInput(String.format("Passenger %s surname:- ", i + 1));
        } else {
            return getUserInput("Passenger surname:- ");
        }
    }

    public static double enterCostForCabin(int i) {

        if (i > 0) {
            return getUserDoubleInput(String.format("Passenger %s cost:- ", i + 1));
        } else {
            return getUserDoubleInput("Passenger cost:- ");
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
