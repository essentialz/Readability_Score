package readability;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = new Document(args[0]);
        System.out.println(doc);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Request request = Request.valueOf(scanner.nextLine().toUpperCase());

        System.out.println(Readability.getScore(doc, request));
    }
}
