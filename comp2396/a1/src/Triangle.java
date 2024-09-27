import java.lang.Math;

/**
 * This class inherits from the Shape class.
 * It represents a triangle shape.
 */
public class Triangle extends Shape {
    /**
     * a const to represent sin(pi/3)
     */
    public static final double SIN_PI_OVER_3 = Math.sin(Math.PI / 3);
    /**
     *  a const to represent cos(pi/3)
     */
    public static final double COS_PI_OVER_3 = Math.cos(Math.PI / 3);

    /**
     * Set the triangle vertices.
     * @param d roughly defines the size of the triangle.
     */
    @Override
    void setVertices(double d) {
        // Calculate the x and y coordinate of the three vertices.
        double xRightVertex = d;
        double yRightVertex = 0;
        double xUpperLeftVertex = -d * COS_PI_OVER_3;
        double yUpperLeftVertex = -d * SIN_PI_OVER_3;
        double xBottomLeftVertex = -d * COS_PI_OVER_3;
        double yBottomLeftVertex = d * SIN_PI_OVER_3;

        // Assign the coordinate to the xLocal and yLocal field of the class.
        // The right vertex comes first, and then the rest are in counter-clockwise order.
        xLocal = new double[] {xRightVertex, xUpperLeftVertex, xBottomLeftVertex};
        yLocal = new double[] {yRightVertex, yUpperLeftVertex, yBottomLeftVertex};
    }
}
