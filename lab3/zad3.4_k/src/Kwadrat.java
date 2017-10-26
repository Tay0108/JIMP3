public class Kwadrat extends Shape {
    Kwadrat() {
        System.out.println("zrobiłem kwadrat");
        Shape.listOfShapes.addLast(this);
    }
    @Override
    public void draw() {
        System.out.println("(rysuje kwadrat)");
    }
}
