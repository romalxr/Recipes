import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String [] sts = s.split(" ");
        List<String> res = new ArrayList<>();
        boolean odd = true;
        for (String st : sts) {
            odd = !odd;
            if (odd) {
                res.add(st);
            }
        }
        Collections.reverse(res);
        res.forEach(a->System.out.print(a + " "));
        System.out.println("");
    }
}