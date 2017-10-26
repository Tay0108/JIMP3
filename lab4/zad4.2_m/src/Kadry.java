import java.util.LinkedList;

public class Kadry {
    public static LinkedList<Pracownik> listaPracownikow = new LinkedList<>();

    public void dodaj(Pracownik pracownik) {
        if (listaPracownikow.contains(pracownik))
            System.out.println("taki pracownik jest już w bazie.");
        else
            System.out.println("dodano");
    }
}
