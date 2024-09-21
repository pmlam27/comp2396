import java.awt.Color;
import java.lang.Math;
import java.util.Arrays;

/**
 * This class is the base class, and it will be inherited by different Shape class implementation.
 */
public class Shape {
    Color color; // color of the shape
    boolean filled; // whether the shape is filled with color
    double theta; // the angle of the shape
    double xc; // x coordinate of the shape center
    double yc; // y coordinate of the shape center
    double[] xLocal; // x coordinates of all the vertices, relative to the shape center
    double[] yLocal; // y coordinates of all the vertices, relative to the shape center

    /**
     * set the vertices of the shape, should be overridden in subclasses.
     * @param d what this value means depends on the implementation. please refer to the subclasses for details.
     */
    void setVertices(double d) {
    }

    /**
     * translates the shape center.
     * @param dx translation in the x axis
     * @param dy translation in the y axis
     */
    void translate(double dx, double dy) {
        xc += dx;
        yc += dy;
    }

    /**
     * rotates the shape.
     * @param dt the amount of rotation to the shape (in radian)
     */
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
            xCanvas[i] = (int) Math.round(xCosTheta - ySinTheta + xc);
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

