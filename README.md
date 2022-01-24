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

## Chap 02. 람다식(Lambda expression)
- 함수형 프로그래밍
- 함수가 외부에 있는 변수를 사용하지 않고, 외부에 side effect을 발생시키지 않는다. 
### 장점
- 동시에 여러 함수가 호출되도 문제가 발생하지 않는다 (병렬처리 가능)
- 똑같은 인풋이 들어갔을 때, 똑같은 아웃풋을 낸다.
- 다양한 매개변수에 대하여 동일한 기능을 처리해준다. 

### 문법((매개변수)->실행문)
1. 람다식 표현
2. 매개변수가 하나인 경우 괄호 생략, 실행문 한 줄인 경우 중괄호 생략
3. 매개변수가 두 개 이상인 경우 괄호 필수
4. return이 들어가면 중괄호 필수
5. return 값만 나타낼 땐, return과 중괄호 둘 다 생략가능
```plain

1. (int x,int y)->{return x+y;}
2. str->System.out.println(str);
3. (x,y)->System.out.println(x+y)
4. str ->{return str.length();}
5. str -> str.length();

```

### 사용하는 경우
1. interface로 정의해놓은 매서드 구현할 때
```java
package tutorial.chap02;

public interface Add {
    public  int add(int x,int y);
}


public class AddTest {
   public static void main(String[] args) {
      Add add = (x, y) -> {
         return x + y;
      };
      int result = add.add(3, 2);
      System.out.println("결과값:"+result);
   }
}


```

## Chap 03. 함수형 인터페이스와 람다식 구현하여 사용하기
@FunctionalInterface    
- 함수형 인터페이스를 의미하는 어노테이션
- 메서드를 두 개 이상 선언하지 못한다.
```java
package tutorial.chap03;

@FunctionalInterface    //메서드를 두 개 이상 선언하지 못한다.
public interface MyNumber {
   int getMax(int num1, int num2);
}

public class MyNumberTest {
    public static void main(String[] args) {
        MyNumber myNumber=(x,y)->{
            return x>y?x:y;
        };

        System.out.println(myNumber.getMax(10,20));
    }
}

```