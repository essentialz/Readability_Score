import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String subString = scanner.nextLine();

        int difference = string.length() - string.replaceAll(subString, "").length();

        System.out.println(difference / subString.length());
    }
}