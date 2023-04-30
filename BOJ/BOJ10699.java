package BOJ;

import java.time.*;
import java.time.format.*;

public class BOJ10699 {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
