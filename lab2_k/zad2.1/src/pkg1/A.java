package pkg1;

public class A {
    int number;
    protected String name;

    public A(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public void callDecrement() {
        this.decrement();
    }
    public void callChangeName() {
        this.changeName();
    }
    public void callIncrement() {
        this.increment();
    }
    private void increment() {
        this.number++;
    }
    protected void decrement() {
        this.number--;
    }
    void changeName() {
        this.name = "changeName class A";
    }
}
