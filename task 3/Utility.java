public class Utility {
    public static boolean AreCabinsFull(Cabin[] cabinRooms) {
        boolean check = false;
        for (int i = 0; i < 12; i++) {
            check = check || cabinRooms[i].isCabinEmpty();
        }
        return check;
    }

    public static`
}
