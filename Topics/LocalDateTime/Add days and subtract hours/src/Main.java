import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.next());
        int daysPlus = scanner.nextInt();
        int hoursMinus = scanner.nextInt();

        System.out.println(dateTime.plusDays(daysPlus).minusHours(hoursMinus));
    }
}