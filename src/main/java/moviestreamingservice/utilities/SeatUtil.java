package moviestreamingservice.utilities;

import java.util.ArrayList;
import java.util.List;

public class SeatUtil {
    public static List<String> generateSeats(int totalSeats) {
        List<String> result = new ArrayList<>();

        int columns = 10;
        int rows = (int)Math.ceil((double)totalSeats / columns);

        for (int i = 0; i < rows; i++) {
            char row = (char)('A' + i);
            for (int j = 1; j <= columns; j++) {
                if(result.size()==totalSeats){
                    return result;
                }
                result.add(row + String.valueOf(j));
            }
        }
        return result;
    }
    public static boolean seatExists(String seat, List<String> allSeats) {
        return allSeats.contains(seat);
    }
}
