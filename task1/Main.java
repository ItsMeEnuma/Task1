package task1;

public class Main {
    public static void main(String[] args) {
        Formula f = new Formula();
        f.prepare("2 * 2 + 2 + 2 * 2");
        System.out.println(f.execute());
    }
}
