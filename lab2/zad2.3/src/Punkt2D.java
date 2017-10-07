public class Punkt2D {
/*    Punkt2D() {
        this.x = 0;
        this.y = 0;
    }*/

    Punkt2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    double distance(Punkt2D point) {
        return Math.sqrt((this.getX() - point.getX()) * (this.getX() - point.getX()) + (this.getY() + point.getY()) * (this.getY() + point.getY()));
    }
}
