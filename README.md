# java-tutorial

## Chap 01. 여러 내부 클래스

1. 인스턴스 내부 클래스 (instance inner class)
   - 다른 클래스가 사용할 일이 거의 없다. (private으로 선언하기)
2. 정적 내부 클래스 (static inner class)

```java
package tutorial.chap01;
class  OutClass{
    private int num=10;
    private static int sNum=20;
    private InClass inClass;

    public OutClass(){
        inClass = new InClass();
    }
    private class InClass{
        int iNum=100;

        void inTest(){
            System.out.println("OutClass num="+num+" (외부 클래스의 인스턴스 변수)");
            System.out.println("OutClass sNum="+sNum+" (외부 클래스의 스태틱 변수)");
            System.out.println("InClass iNum="+iNum+" (내부 클래스의 인스턴스 변수)");
        }
    }

    //OutClass의 메서드
    public void usingClass(){
        inClass.inTest();
    }

    static class InStaticClass{
        int iNum=100;
        static  int sInNum=200;

        void inTest(){
            System.out.println("정적 내부 클래스에서는 외부 클래스의 인스턴스 변수 사용 못함");
            System.out.println("InClass num="+iNum+" (내부 클래스의 인스턴스 변수)");
            System.out.println("OutClass sNum="+sNum+" (외부 클래스의 스태틱 변수)");
            System.out.println("InClass sInNum="+sInNum+" (내부 클래스의 스태틱 변수)");
        }

        static void sTest(){
            System.out.println("정적 내부 클래스에서는 외부 클래스의 인스턴스 변수 사용 못함");
            System.out.println("내부 클래스의 인스턴스 변수 사용못함");
            System.out.println("OutClass sNum="+sNum+" (외부 클래스의 스태틱 변수)");
            System.out.println("InClass sInNum="+sInNum+" (내부 클래스의 스태틱 변수)");
        }
    }

}
public class InnerTest {
    public static void main(String[] args) {
        OutClass outClass = new OutClass();
        outClass.usingClass();

        System.out.println();

        OutClass.InStaticClass sInClass = new OutClass.InStaticClass();
        sInClass.inTest();

        System.out.println();

        OutClass.InStaticClass.sTest();

    }

}


```
3. 지역 내부 클래스 (local inner class)
```java

package tutorial.chap01.ex03;

class Outer2{

    int outNum=100;
    static int sNum=200;

    //메서드 (매개변수 호출될 때 stack 메모리에 생성된다)
    Runnable getRunnable(final int i){
        int num=10;
        // Runnable을 상속받는 지역내부클래스 MyRunnable
        class MyRunnable implements Runnable{
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
        }
        return new MyRunnable();
    }
}

public class AnnoymousInnerTest {
    public static void main(String[] args) {
        Outer2 outer2 = new Outer2();
        Runnable runnable = outer2.getRunnable(10);

        runnable.run();
    }
}

```
4. 익명 내부 클래스 ** (anonymous inner class)
- 로컬내부 클래스는 클래스명이 필요없어 익명내부클래스 이용.
```java
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


```