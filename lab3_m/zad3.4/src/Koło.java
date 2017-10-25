import java.util.LinkedList;

public class Koło extends Shape{
    Koło() {
        System.out.println("Zrobiłem koło");
        Shape.listOfShapes.addLast(this);
    }
    public void draw() {
        System.out.println("(rysuje koło)");
    }
}
