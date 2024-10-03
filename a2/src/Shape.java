import java.awt.Color;

/**
 * the Shape class can be used to represent a shape, such as a polygon
 */
public class Shape {
    private Color color;
    private boolean filled;
    private double theta;
    private double xc;
    private double yc;
    private double[] xLocal;
    private double[] yLocal;

    /**
     * get the color
     * @return Color class that represents the color of the shape
     */
    public Color getColor() {
        return color;
    }

    /**
     * get whether the shape is filled
     * @return boolean, true if filled, false if not filled
     */
    public boolean getFilled() {
        return filled;
    }

    /**
     * get the angle of the shape
     * @return a double in radian
     */
    public double getTheta() {
        return theta;
    }

    /**
     * get the x coordinate of the center
     * @return a double
     */
    public double getXc() {
        return xc;
    }

    /**
     * get the y coordinate of the center
     * @return a double
     */
    public double getYc() {
        return yc;
    }

    /**
     * get the x coordinates of the vertices relative to the center
     * @return a double array containing all the x coordinates
     */
    public double[] getxLocal() {
        return xLocal;
    }

    /**
     * get the y coordinates of the vertices relative to the center
     * @return a double array containing all the y coordinates
     */
    public double[] getyLocal() {
        return yLocal;
    }

    /**
     * set the color of the shape
     * @param color should a Color class
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * set whether the shape is filled
     * @param filled true if filled, false if not filled
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * set the angle of the shape
     * @param theta a double in radian
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     * set the x coordinate of the center
     * @param xc a double
     */
    public void setXc(double xc) {
        this.xc = xc;
    }

    /**
     * set the y coordinate of the center
     * @param yc a double
     */
    public void setYc(double yc) {
        this.yc = yc;
    }

    /**
     * set the x coordinates of the vertices relative to the center
     * @param xLocal a double array containing all the x coordinates
     */
    public void setXLocal(double[] xLocal) {
        this.xLocal = xLocal;
    }

    /**
     * set the y coordinates of the vertices relative to the center
     * @param yLocal a double array containing all the y coordinates
     */
    public void setYLocal(double[] yLocal) {
        this.yLocal = yLocal;
    }

    /**
     * translate the location (center) of the shape
     * @param dx desired change in x coordinate
     * @param dy desired change in y coordinate
     */
    public void translate(double dx, double dy) {
        this.xc += dx;
        this.yc += dy;
    }

    /**
     * rotate the shape by a certain amount
     * @param dt the amount of rotation, in radian
     */
    public void rotate(double dt) {
        this.theta += dt;
    }

    /**
     * This method translate the x coordinates from local to canvas coordinate, and then return it.
     * @return an array of integer representing the x coordinate relative to the canvas.
     */
    public int[] getX() {

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
    public int[] getY() {

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
