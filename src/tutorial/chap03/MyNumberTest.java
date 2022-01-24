package tutorial.chap03;

public class MyNumberTest {
    public static void main(String[] args) {
        MyNumber myNumber=(x,y)->{
            return x>y?x:y;
        };

        System.out.println(myNumber.getMax(10,20));
    }
}
