
import java.util.*;
import ohtu.Multiplier;
public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
        int three = 3;
        Multiplier kolme = new Multiplier(three);
        System.out.println("anna luku ");
        int luku = scanner.nextInt();
        
        System.out.println("luku kertaa kolme on "+kolme.multipliedBy(luku) );


    }
}
