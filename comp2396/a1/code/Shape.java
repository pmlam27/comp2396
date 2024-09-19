import java.awt.Color;
import java.lang.Math;

public class Shape {
    Color color;
    boolean filled;
    double theta;
    double xc;
    double yc;
    double[] xLocal;
    double[] yLocal;

    void setVertices(double d) {
    }

    void translate(double dx, double dy) {
        xc += dx;
        yc += dy;
    }

    void rotate(double dt) {
        theta += dt;
    }

    int[] getX() {
        int xAmount = xLocal.length;
        int[] xCanvas = new int[xAmount];
        for(int i=0; i<xAmount; i++) {
            // compute x*cos(theta) and y*sin(theta)
            double xCosTheta = xLocal[i] * Math.cos(theta);
            double ySinTheta = yLocal[i] * Math.sin(theta);

            // it is assumed that the x coordinate is within -2^16 < x < 2^16 (size of int)
            xCanvas[i] = (int) Math.round(xCosTheta + ySinTheta + xc);
        }
        return xCanvas;
    }

    int[] getY() {
        int yAmount = yLocal.length;
        int[] yCanvas = new int[yAmount];
        for(int i=0; i<yAmount; i++) {
            // compute x*sin(theta) and y*cos(theta)
            double xSinTheta = xLocal[i] * Math.sin(theta);
            double yCosTheta = yLocal[i] * Math.cos(theta);

            yCanvas[i] = (int) Math.round(xSinTheta + yCosTheta + yc);
        }
        return yCanvas;
    }
 }

