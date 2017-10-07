public class Punkt3D extends Punkt2D {
    Punkt3D(double x, double y, double z) {
        super(x,y); // dzieki temu nie musimy w klasie bazowej miec konstruktora bezparametrowego, bo wołamy parametrowy
        this.z = z;
    }

    private double z;

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    double distance(Punkt3D point) {
        return Math.sqrt((getX()-point.getX())*(getX()-point.getX()) + (getY()-point.getY())*(getY()-point.getY()) + (getZ()-point.getZ())*(getZ()-point.getZ()));
    }
}
