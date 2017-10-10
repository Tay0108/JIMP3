import pkg1.*;
import pkg2.*;

public class Main {
    public static void main(String[] args) {
        A a = new A(0, "test");
        B b = new B(0, "test");
        C c = new C(0, "test");

        a.callIncrement();
        a.callChangeName();
        a.callDecrement();

        b.callIncrement();
        b.callChangeName();
        b.callDecrement();

        c.callIncrement();
        c.callChangeName();
        c.callDecrement();
    }
}
