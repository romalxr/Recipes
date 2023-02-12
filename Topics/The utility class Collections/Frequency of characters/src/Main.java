import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        final List<String> seq = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        final String s = scanner.nextLine();

        System.out.println(Collections.frequency(seq, s));
    }
}