public class Main_v2 {
    public static void main(String[] args) {
        Punkt2d_v2 p1 = new Punkt2d_v2(0,0);
        System.out.println(p1.getX());
        System.out.println(p1.getY());
        Punkt2d_v2 p2 = new Punkt2d_v2(1,1);
        System.out.println(p1.distance(p2));
    }
}
