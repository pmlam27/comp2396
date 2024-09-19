
public class Square extends Shape {
    @Override
    void setVertices(double d) {
        // the order is counter-clockwise, starting with the +x, -y quadrant
        xLocal = new double[] {d, d, -d, -d};
        yLocal = new double[] {d, -d, -d, d};
    }
}
