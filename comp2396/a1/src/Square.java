/**
 * This class inherits from the Shape class.
 * It represents a square shape.
 */
public class Square extends Shape {
    /**
     * Set the vertices of the shape.
     * @param d is 1/2 of the side length of the Square.
     */
    @Override
    void setVertices(double d) {
        // the order is counter-clockwise, starting with the +x, -y quadrant
        xLocal = new double[] {d, d, -d, -d};
        yLocal = new double[] {d, -d, -d, d};
    }
}
