import java.awt.Color;
import java.lang.Math;
import java.util.Arrays;

/**
 * This class is the base class, and it will be inherited by different Shape class implementation.
 */
public class Shape {
    /**
     * color of the shape
     */
    public Color color;
    /**
     * whether the shape is filled with color
     */
    public boolean filled;
    /**
     * the rotation of the shape
     */
    public double theta;
    /**
     * x coordinate of the shape center
     */
    public double xc;
    /**
     * y coordinate of the shape center
     */
    public double yc;
    /**
     * x coordinates of all the vertices, relative to the shape center
     */
    public double[] xLocal;
    /**
     * y coordinates of all the vertices, relative to the shape center
     */
    public double[] yLocal;

    /**
     * this method will not do anything in the Shape class.
     * It should be overridden in subclasses, in which this method will set the vertices of the shape.
     * @param d this value roughly defines the size of the shape. Exact implementation will differ in each subclass.
     */
    void setVertices(double d) {
    }

    /**
     * Translates the center of the shape based on the provided values.
     * @param dx translation in the x-axis
     * @param dy translation in the y-axis
     */
    void translate(double dx, double dy) {
        xc += dx;
        yc += dy;
    }

    /**
     * Rotates the shape based on the provided rotation angle.
     * @param dt the amount of rotation to the shape (in radian)
     */
    void rotate(double dt) {
        theta += dt;
    }

    /**
     * This method translate the x coordinates from local to canvas coordinate, and then return it.
     * @return an array of integer representing the x coordinate relative to the canvas.
     */
    int[] getX() {

        // Get the number of vertices that the shape has. Assumes that each vertex have exactly one x coordinate.
        int verticesAmount = xLocal.length;

        // Create an integer array to store the translation result.
        int[] xRelativeToCanvas = new int[verticesAmount];

        // For each vertex, translate the vertex and store it in the xRelativeToCanvas array.
        for(int i=0; i<verticesAmount; i++) {

            // Compute x*cos(theta) and y*sin(theta) for later use
            double xCosTheta = xLocal[i] * Math.cos(theta);
            double ySinTheta = yLocal[i] * Math.sin(theta);

            // Compute the x coordinates relative to canvas and store it.
            // It is assumed that the x coordinate is within -2^16 < x < 2^16-1 (size of int)
            xRelativeToCanvas[i] = (int) Math.round(xCosTheta - ySinTheta + xc);
        }

        // Returns the array.
        return xRelativeToCanvas;
    }

    /**
     * This method translate the y coordinates from local to canvas coordinate, and then return it.
     * @return an array of integer representing the y coordinate relative to the canvas.
     */
    int[] getY() {

        // Get the number of vertices that the shape has. Assumes that each vertex have exactly one y coordinate.
        int verticesAmount = yLocal.length;

        // Create an integer array to store the translation result.
        int[] yRelativeToCanvas = new int[verticesAmount];

        // For each vertex, translate the vertex and store it in the yRelativeToCanvas array.
        for(int i=0; i<verticesAmount; i++) {
            // compute x*sin(theta) and y*cos(theta) for later use
            double xSinTheta = xLocal[i] * Math.sin(theta);
            double yCosTheta = yLocal[i] * Math.cos(theta);

            // Compute the y coordinates relative to canvas and store it.
            // It is assumed that the y coordinate is within -2^16 < x < 2^16-1 (size of int)
            yRelativeToCanvas[i] = (int) Math.round(xSinTheta + yCosTheta + yc);
        }

        // Returns the array.
        return yRelativeToCanvas;
    }
 }

