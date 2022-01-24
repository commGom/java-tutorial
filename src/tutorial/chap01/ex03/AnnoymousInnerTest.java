package tutorial.chap01.ex03;

class Outer2{

    int outNum=100;
    static int sNum=200;

    //메서드 (매개변수 호출될 때 stack 메모리에 생성된다)
    Runnable getRunnable(final int i){
        int num=10;
        // Runnable을 상속받는 지역내부클래스 MyRunnable
        //class MyRunnable implements Runnable -> 클래스이름을 딱히 쓰지 않기 때문에
        return new Runnable(){
            int localNum=1000;
            @Override
            public void run() {
                //i=50; -> 안되는 이유, 메모리에 올라오고 나서 변경해야하는데 매개변수는 호출이 되었을 때 stack 메모리에 쌓이기 때문에

                System.out.println("OutClass outNum="+outNum+" (외부 클래스의 인스턴스 변수)");
                System.out.println("OutClass sNum="+sNum+" (외부 클래스의 스태틱 변수)");

                System.out.println("");
                System.out.println("InClass i="+i+" (메서드 안에 선언된 매개변수)");
                System.out.println("OutClass localNum="+localNum+" (지역 내부 클래스안의 인스턴스변수 변수)");
            }
        };
    }
}

public class AnnoymousInnerTest {
    public static void main(String[] args) {
        Outer2 outer2 = new Outer2();
        Runnable runnable = outer2.getRunnable(10);

        runnable.run();

        //

        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable class");
            }
        };
    }
}
