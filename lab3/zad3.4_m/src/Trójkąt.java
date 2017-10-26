public class Trójkąt extends Shape {
    Trójkąt() {
        System.out.println("Zrobiłem trójkąt");
        Shape.listOfShapes.addLast(this);
    }
    @Override
    public void draw() {
        System.out.println("(rysuje trójkąt)");
    }

    public Trójkąt getMe() {
        return this;
    }
}
