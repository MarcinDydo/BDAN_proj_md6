public class Main {
    public static void main(String[] args) {
        MD6HashFunction m = new MD6HashFunction(512);
        m.GenerateHash("eluwina");
    }
}
