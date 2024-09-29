import java.awt.Color;

public class Shape {
    private Color color;
    private boolean filled;
    private double theta;
    private double xc;
    private double yc;
    private double[] xLocal;
    private double[] yLocal;

    public Color getColor() {
        return color;
    }

    public boolean getFilled() {
        return filled;
    }

    public double getTheta() {
        return theta;
    }

    public double getXc() {
        return xc;
    }

    public double getYc() {
        return yc;
    }

    public double[] getxLocal() {
        return xLocal;
    }

    public double[] getyLocal() {
        return yLocal;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setXc(double xc) {
        this.xc = xc;
    }

    public void setYc(double yc) {
        this.yc = yc;
    }

    public void setXLocal(double[] xLocal) {
        this.xLocal = xLocal;
    }

    public void setYLocal(double[] yLocal) {
        this.yLocal = yLocal;
    }

    public void translate(double dx, double dy) {
        this.xc += dx;
        this.yc += dy;
    }

    public void rotate(double dt) {
        this.theta += dt;
    }

    public int[] getX() {
        return new int[] {};
    }

    public int[] getY() {
        return new int[] {};
    }
}
