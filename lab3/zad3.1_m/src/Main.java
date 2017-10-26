import java.util.LinkedList;

public class Main {

    private static LinkedList<Double> list = new LinkedList<Double>();

    public static void main(String[] args) {
        String p1 = "kupa";
        int p2 = 23;
        double p3 = 88.88888888;
        Ziemniak p4 = new Ziemniak("Jacek", 5);
        Object p5 = new Ziemniak("mam dauna", 10);

        Object tab[] = new Object[6];

        tab[0] = p1;
        tab[1] = p2;
        tab[2] = p3;
        tab[3] = p4.imie;
        tab[4] = p4;
        tab[5] = p5;

        for (Object o: tab) {
            System.out.println(o.getClass() + "         " + o);
        }

        int t1 = p4.increment(15);
        System.out.println(t1);

        Double d = new Double(4);
        list.addLast(d);

        p4.changeFinalTest(list);

        for (Double i : list ) {
            System.out.println(i);
        }

    }
}
