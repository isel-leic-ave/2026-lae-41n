class MyObject {
    public final static MyObject INSTANCE = new MyObject();
    private MyObject() { }

    public void foo() {
        System.out.println("I'm foo");
    }

    public void bar() {
        System.out.println("I'm bar");
    }

}

class MyObjectMain {
    static void main() {

        MyObject mo = new MyObject();
        MyObject.INSTANCE.foo();
        MyObject.INSTANCE.bar();
    }
}