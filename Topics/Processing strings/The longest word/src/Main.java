import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String largest = scanner.next();

        while (scanner.hasNext()) {
            String next = scanner.next();
            largest = largest.length() < next.length() ? next : largest;
        }

        System.out.println(largest);
    }
}