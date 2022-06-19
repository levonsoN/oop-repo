import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input;
        while (true) {
            try {
                System.out.println("Enter line: ");
                input = s.nextLine();
                System.out.println("Enter word length k: ");
                int k = s.nextInt();
                System.out.println("Enter character to replace: ");
                char c = s.next().charAt(0);
                break;
            } catch (Exception ex) {
                System.out.println("Invalid input. Try again.");
            }
        }
        String result = new Replacer().replace(input, 3, 'c');
        System.out.println("Result: ");
        System.out.println(result);
        s.close();
    }
}
