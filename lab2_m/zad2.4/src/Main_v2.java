public class Main_v2 {
    public static void main(String[] args) {
        Punkt3d_v2 p1 = new Punkt3d_v2(0,0,0);
        System.out.println(p1.getX());
        System.out.println(p1.getY());
        System.out.println(p1.getZ());
        Punkt3d_v2 p2 =  new Punkt3d_v2(1,1,1);
        System.out.println(p1.distance(p2));
    }
}
