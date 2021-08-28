import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        StringBuilder output = new StringBuilder();

        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (output.length() <= 0) {
                output.append(input.charAt(i));
                count++;
            } else {
                char prev = output.charAt(output.length() - 1);

                if (input.charAt(i) == prev) {
                    count++;
                } else {
                    output.append(count);
                    output.append(input.charAt(i));
                    count = 1;
                }
            }

            if (i == input.length() - 1) {
                output.append(count);
            }

        }

        System.out.println(output);

    }
}