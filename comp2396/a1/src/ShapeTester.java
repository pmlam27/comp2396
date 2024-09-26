import java.awt.Color;
import java.util.Arrays;

/**
 * This class checks the correctness of the Shape class
 */
public class ShapeTester {

    final Color defaultColor = new Color(0, 0, 0);
    final boolean defaultFilled = true;
    final double defaultTheta = 0.0;
    final double defaultXc = 10.0;
    final double defaultYc = 10.0;
    final double[] defaultXLocal = new double[] {50, 50, -50, -50};
    final double[] defaultYLocal = new double[] {50, -50, -50, 50};

    public static void main(String[] args) {
        ShapeTester tester = new ShapeTester();
        System.out.print(tester.testAllAndGetMessage());
    }

    /**
     * create a new instance of shape using default values
     */
    Shape createTestShape() {
        Shape shape = new Shape();
        shape.color = defaultColor;
        shape.filled = defaultFilled;
        shape.theta = defaultTheta;
        shape.xc = defaultXc;
        shape.yc = defaultYc;
        shape.xLocal = defaultXLocal;
        shape.yLocal = defaultYLocal;
        return shape;
    }

    boolean testAllFields() {
        Shape testShape = createTestShape();
        return  testShape.color == defaultColor &&
                testShape.filled == defaultFilled &&
                testShape.theta == defaultTheta &&
                testShape.xc == defaultXc &&
                testShape.yc == defaultYc &&
                testShape.xLocal == defaultXLocal &&
                testShape.yLocal == defaultYLocal;
    }

    /**
     * set vertices should not change anything
     * @return
     */
    boolean testSetVertices() {
        Shape testShape = createTestShape();
        testShape.setVertices(10);

        // test if any values of testShape have changed.
        return  testShape.color == defaultColor &&
                testShape.filled == defaultFilled &&
                testShape.theta == defaultTheta &&
                testShape.xc == defaultXc &&
                testShape.yc == defaultYc &&
                testShape.xLocal == defaultXLocal &&
                testShape.yLocal == defaultYLocal;
    }

    boolean testTranslate() {
        Shape testShape = createTestShape();
        testShape.translate(100.0, -100.0);
        System.out.println();
        // test if translate have successfully changed the center values.
        return testShape.xc == 110.0 && testShape.yc == -90.0;
    }

    boolean testRotate() {
        Shape testShape = createTestShape();
        testShape.rotate(Math.PI / 4);

        // test if rotate have successfully changed the theta value.
        return testShape.theta == Math.PI / 4;
    }

    boolean testGetX() {
        Shape testShape = createTestShape();
        int[] supposedXValues = new int[] {60, 60, -40, -40};
        int[] getXResult = testShape.getX();
        return Arrays.equals(supposedXValues, getXResult);
    }

    boolean testGetY() {
        Shape testShape = createTestShape();
        int[] supposedYValues = new int[] {60, -40, -40, 60};
        int[] getYResult = testShape.getY();
        return Arrays.equals(supposedYValues, getYResult);
    }

    /**
     * test everything and return a message
     * @return a string that should be printed to indicate whether the tests are successful or not.
     */
    String testAllAndGetMessage() {

        return "\n---Shape Test---\n" + // message heading
                (testSetVertices() ? "setVertices test: passed" : "setVertices test: failed") + "\n" +
                (testAllFields() ? "all fields test: passed" : "all fields test: failed") + "\n" +
                (testTranslate() ? "translation test: passed" : "translation test: failed") + "\n" +
                (testRotate() ? "rotate test: passed" : "rotate test: failed") + "\n" +
                (testGetX() ? "getX test: passed" : "getX test: failed") + "\n" +
                (testGetY() ? "getY test: passed" : "getY test: failed") + "\n";
    }
}
