public class Punkt2d_v2 {
    protected double x;
    protected double y;

    Punkt2d_v2(double _x, double _y) {
        x = _x;
        y = _y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double _x) {
        x = _x;
    }

    public void setY(double _y) {
        y = _y;
    }

    double distance(Punkt2d_v2 point) {
        return Math.sqrt(Math.pow((point.y - y),2) + Math.pow((point.x - x),2));
    }
}
