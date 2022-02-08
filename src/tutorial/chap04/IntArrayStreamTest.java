package tutorial.chap04;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntArrayStreamTest {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        for (int num : arr) {
            System.out.println(num);
        }

        Arrays.stream(arr).forEach(num-> System.out.print(num));
        System.out.println();
        IntStream stream = Arrays.stream(arr);
        int sum = stream.sum();
        System.out.println(sum);
        //한 번 사용한 stream은 재사용 하지 못한다.
    }
}
