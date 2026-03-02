// Foo.java
class X {
    public void print() {
        System.out.println("I am X.");
    }
    public void anotherPrint() {
        System.out.println("I am X from anotherPrint");
    }
}
interface Y {
    void print();

    public static void main(String[] args) {
        System.out.println("Hello from Y");
    }
}
class Z {
    public void print() {
        System.out.println("I am Z");
    }

    public static void main(String[] args) {
        System.out.println("Hello from Z");
        }

}