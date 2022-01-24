package tutorial.chap01.ex01;
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
