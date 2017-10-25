public class Trojkat extends Shape {
    Trojkat() {
        System.out.println("Zrobiłem trójkąt");
        Shape.listOfShapes.addLast(this);
    }
    @Override
    public void draw() {
        System.out.println("(rysuje trójkąt)");
    }

    public Trojkat getMe() {
        return this;
    }
}
