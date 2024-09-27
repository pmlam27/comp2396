/**
 * This class inherits from the Shape class.
 * It represents a circle shape.
 */
public class Circle extends Shape {
    /**
     * set the vertices for the circle's bounding box.
     * @param d d is 1/2 of the side length of the bounding box.
     */
    @Override
    void setVertices(double d) {
        // Assign the coordinate to the xLocal and yLocal field of the class.
        // The order is upper left first, and then lower right.
        xLocal = new double[] {-d, d};
        yLocal = new double[] {-d, d};
    }

    /**
     * This method translate the x coordinates from local to canvas coordinate, and then return it.
     * @return an integer array representing the bounding box's x coordinate relative to the canvas.
     */
    @Override
    int[] getX() {
        // Compute the x coordinate of the vertices relative to the canvas.
        // It is assumed that the x coordinate is within -2^16 < x < 2^16-1 (size of int)
        int xUpperLeftCanvas = (int) Math.round(xLocal[0] + xc);
        int xLowerRightCanvas = (int) Math.round(xLocal[1] + xc);

        // Returns the x coordinate relative to the canvas.
        return new int[] {xUpperLeftCanvas, xLowerRightCanvas};
    }

    /**
     * This method translate the y coordinates from local to canvas, and then return it.
     * @return an integer array representing the bounding box's y coordinate relative to the canvas.
     */
    @Override
    int[] getY() {
        // Compute the y coordinate of the vertices relative to the canvas.
        // It is assumed that the y coordinate is within -2^16 < x < 2^16-1 (size of int)
        int yUpperLeftCanvas = (int) Math.round(yLocal[0] + yc);
        int yLowerRightCanvas = (int) Math.round(yLocal[1] + yc);

        // Returns the y coordinate relative to the canvas.
        return new int[] {yUpperLeftCanvas, yLowerRightCanvas};
    }
}
