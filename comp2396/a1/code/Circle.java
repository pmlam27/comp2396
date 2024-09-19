public class Circle extends Shape {
    @Override
    void setVertices(double d) {
        // the order is upper left first, and then lower right
        xLocal = new double[] {-d, d};
        yLocal = new double[] {-d, d};
    }

    @Override
    int[] getX() {
        // TODO: more descriptive name?
        int xUpperLeft = (int) Math.round(xLocal[0] + xc);
        int xLowerRight = (int) Math.round(xLocal[1] + xc);

        return new int[] {xUpperLeft, xLowerRight};
    }

    @Override
    int[] getY() {

        int yUpperLeft = (int) Math.round(yLocal[0] + yc);
        int yLowerRight = (int) Math.round(yLocal[1] + yc);

        return new int[] {yUpperLeft, yLowerRight};
    }
}
