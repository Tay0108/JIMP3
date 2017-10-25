import java.util.LinkedList;

public abstract class Shape {
    public String name;
    public static LinkedList<Object> listOfShapes = new LinkedList<Object>();

    /**
     * Metoda rysująca w konsoli dany kształt
     */
    public abstract void draw();
}
