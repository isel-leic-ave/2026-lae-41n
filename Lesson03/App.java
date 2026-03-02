// App.java
public class App {
    public static void main(String[] args) {
        System.out.println("Press ENTER to proceed.");
        String s = System.console().readLine();
        //if(s.equals("yes")) {
            new X().print();
        //}
    }
    public static void bar() {
        new Z().print();
    }
}

