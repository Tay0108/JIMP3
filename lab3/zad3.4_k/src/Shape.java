import java.util.LinkedList;

public abstract class Shape {
    public String name;
    public static LinkedList<Shape> listOfShapes = new LinkedList<Shape>();

    /**
     * Metoda rysująca w konsoli dany kształt
     */
    public abstract void draw();
}
