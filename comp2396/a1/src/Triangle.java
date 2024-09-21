import java.lang.Math;

public class Triangle extends Shape {
    private static final double SIN_PI_OVER_3 = Math.sin(Math.PI / 3);
    private static final double COS_PI_OVER_3 = Math.cos(Math.PI / 3);

    @Override
    void setVertices(double d) {
        double xRightVertex = d;
        double yRightVertex = 0;
        double xUpperLeftVertex = -d * COS_PI_OVER_3;
        double yUpperLeftVertex = -d * SIN_PI_OVER_3;
        double xBottomLeftVertex = -d * COS_PI_OVER_3;
        double yBottomLeftVertex = d * SIN_PI_OVER_3;

        xLocal = new double[] {xRightVertex, xBottomLeftVertex, xUpperLeftVertex};
        yLocal = new double[] {yRightVertex, yBottomLeftVertex, yUpperLeftVertex};
    }
}
