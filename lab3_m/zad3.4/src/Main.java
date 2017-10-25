import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Kwadrat k1 = new Kwadrat();
        Koło k2 = new Koło();
        Trójkąt k3 = new Trójkąt();

/*        //Wywołanie metod z obiektów klas pochodnych
        k1.draw();
        k2.draw();
        k3.draw();*/

        //wywołnie metod, korzystając z metody abstarkcyjnej klasy podstawowej
        System.out.println(Shape.listOfShapes);

        LinkedList<Object> jakasLista= new LinkedList<>();

        jakasLista.addLast(k1);
        jakasLista.addLast(k2);
        jakasLista.addLast(k3);

        //TODO narawic to tam żeby można było wywoływać metodę draw() z listy obiektów dziedziczących po klasie Shape
    }
}
