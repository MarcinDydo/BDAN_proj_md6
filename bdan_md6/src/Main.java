import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MD6HashFunction m = new MD6HashFunction();
        System.out.println("Generated hash:");
        System.out.println(m.GenerateHash("abc"));
    }
}
