
import java.util.Arrays;


class A {
    public int x;

    public void m1(int x) { }
    public void m1(String s) { }
}

class B extends A {
    private int y;
    public B(int y) { this.y = y;}
    public void m2(String s) { }

    class D {

    }
}



public class Reflect {


    public static void checkMembers(Object obj) {
        Class<?> objType = obj.getClass();
        System.out.println("obj = " + objType.getName());
        Arrays.asList(objType.getDeclaredMethods())
                .forEach(m -> System.out.println("Method " + m.getName()));

        Arrays.asList(objType.getDeclaredFields())
                .forEach(f -> {
                    System.out.println("Field Name:" + f.getName());
                    try {
                        f.setAccessible(true);
                        System.out.println("Field Value:" + f.get(obj));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static void main(String[] args) {
        System.out.println("Hello");
        checkMembers(new B(5));
        checkMembers(new B(10));
    }
}
