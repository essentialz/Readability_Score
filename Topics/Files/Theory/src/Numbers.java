import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Numbers {
    public static void main(String[] args) {
        String path = "C:\\Users\\essen\\Downloads\\dataset_91007.txt";
        long sum = 0;

        try (Stream<String> fileStream = Arrays.stream(Files.readString(Paths.get(path)).split(" "))) {
            sum = fileStream.mapToInt(Integer::parseInt).max().getAsInt();
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }

        System.out.println(sum);
    }
}
