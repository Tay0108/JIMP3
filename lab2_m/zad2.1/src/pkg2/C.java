package pkg2;
import pkg1.*;

public class C extends B {
    public C(int number, String name) {
        super(number, name);
    }
    void changeName() {
        this.name = "changeName class C";
    }
}
