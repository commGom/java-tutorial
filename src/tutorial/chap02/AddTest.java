package tutorial.chap02;

public class AddTest {
    public static void main(String[] args) {
        Add add = (x, y) -> {
            return x + y;
        };
        int add1 = add.add(3, 2);
        System.out.println("결과값:"+add1);
    }
}
