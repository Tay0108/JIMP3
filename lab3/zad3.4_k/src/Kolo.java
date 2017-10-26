import java.util.LinkedList;

public class Kolo extends Shape{
    Kolo() {
        System.out.println("Zrobiłem koło");
        Shape.listOfShapes.addLast(this);
    }
    public void draw() {
        System.out.println("(rysuje koło)");
    }
}
