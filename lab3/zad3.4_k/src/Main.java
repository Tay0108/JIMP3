import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Kwadrat k1 = new Kwadrat();
        Kolo k2 = new Kolo();
        Trojkat k3 = new Trojkat();

/*        //Wywołanie metod z obiektów klas pochodnych
        k1.draw();
        k2.draw();
        k3.draw();*/

        //wywołnie metod, korzystając z metody abstarkcyjnej klasy podstawowej
        System.out.println(Shape.listOfShapes);

        LinkedList<Shape> jakasLista= new LinkedList<>();

        jakasLista.addLast(k1);
        jakasLista.addLast(k2);
        jakasLista.addLast(k3);



        //TODO narawic to tam żeby można było wywoływać metodę draw() z listy obiektów dziedziczących po klasie Shape
        // Ależ proszę Misiu (blad polegal na tym, ze tworzylas liste z typami Object, a ten typ nie ma metody draw(). Trzeba robic liste typu Shape :) Dobranoc.):
        jakasLista.get(0).draw();
    }
}
