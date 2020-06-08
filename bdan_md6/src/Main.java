import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException {
        MD6HashFunction m = new MD6HashFunction();
        System.out.println("Generated hash:");
        //byte[] test = m.toByteArray("abc", "abc".length());
        byte[] test2 = loadFile("res/2009-04-15-md6-report.pdf");
        System.out.println(m.GenerateHash(test2));
    }
    public static byte[] loadFile(String filePath) throws IOException {
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }
 }
