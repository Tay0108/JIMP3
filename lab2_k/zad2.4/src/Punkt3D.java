public class Punkt3D extends Punkt2D {

    private double z;

    Punkt3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    double distance(Punkt3D point) {
        return Math.sqrt((getX() - point.getX()) * (getX() - point.getX()) + (getY() - point.getY()) * (getY() - point.getY()) + (getZ() - point.getZ()) * (getZ() - point.getZ()));
    }
}
