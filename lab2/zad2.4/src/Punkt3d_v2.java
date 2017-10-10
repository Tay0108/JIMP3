public class Punkt3d_v2 extends Punkt2d_v2 {
    double z;

    Punkt3d_v2(double _x, double _y, double _z) {
        super(_x,_y);
        z = _z;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double _z) {
        z = _z;
    }

    public double distance(Punkt3d_v2 point) {
        return Math.sqrt(Math.pow((point.x - x),2) + Math.pow((point.y - y),2) + Math.pow((point.z - z),2));
    }
}
