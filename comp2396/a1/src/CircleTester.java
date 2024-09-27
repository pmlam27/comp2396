import java.awt.Color;
import java.util.Arrays;

/**
 * This class checks the correctness of the Shape class
 */
public class CircleTester {

    final Color defaultColor = new Color(0, 0, 0);
    final boolean defaultFilled = true;
    final double defaultTheta = 0.0;
    final double defaultXc = 10.0;
    final double defaultYc = 10.0;

    public static void main(String[] args) {
        CircleTester tester = new CircleTester();
        System.out.print(tester.testAllAndGetMessage());
    }

    /**
     * create a new instance of circle using default values
     */
    Circle createTestCircle() {
        Circle circle = new Circle();
        circle.color = defaultColor;
        circle.filled = defaultFilled;
        circle.theta = defaultTheta;
        circle.xc = defaultXc;
        circle.yc = defaultYc;
        // xLocal and yLocal will be set in test methods, so it is null for now.
        circle.xLocal = null;
        circle.yLocal = null;
        return circle;
    }

    boolean testAllFields() {
        Circle testCircle = createTestCircle();

        return  testCircle.color == defaultColor &&
                testCircle.filled == defaultFilled &&
                testCircle.theta == defaultTheta &&
                testCircle.xc == defaultXc &&
                testCircle.yc == defaultYc &&
                testCircle.xLocal == null &&
                testCircle.yLocal == null;
    }

    /**
     * test the set vertices method
     * @return
     */
    boolean testSetVertices() {
        Circle testCircle = createTestCircle();
        testCircle.setVertices(5.0);
        double[] supposedXLocalValue = new double[] {-5.0, 5.0};
        double[] supposedYLocalValue = new double[] {-5.0, 5.0};

        return  Arrays.equals(supposedXLocalValue, testCircle.xLocal) &&
                Arrays.equals(supposedYLocalValue, testCircle.yLocal);
    }

    boolean testTranslate() {
        Circle testCircle = createTestCircle();
        testCircle.translate(100.0, -100.0);
        System.out.println();
        // test if translate have successfully changed the center values.
        return testCircle.xc == 110.0 && testCircle.yc == -90.0;
    }

    boolean testRotate() {
        Circle testCircle = createTestCircle();
        testCircle.rotate(Math.PI / 4);

        // test if rotate have successfully changed the theta value.
        return testCircle.theta == Math.PI / 4;
    }

    boolean testGetX() {
        Circle testCircle = createTestCircle();
        testCircle.xLocal = new double[] {-10.0, 10.0};
        testCircle.yLocal = new double[] {-10.0, 10.0};
        int[] supposedXValues = new int[] {0, 20};
        int[] getXResult = testCircle.getX();
        return Arrays.equals(supposedXValues, getXResult);
    }

    boolean testGetY() {
        Circle testCircle = createTestCircle();
        testCircle.xLocal = new double[] {-10.0, 10.0};
        testCircle.yLocal = new double[] {-10.0, 10.0};
        int[] supposedYValues = new int[] {0, 20};
        int[] getYResult = testCircle.getY();
        return Arrays.equals(supposedYValues, getYResult);
    }

    String testAllAndGetMessage() {
        return  "\n---Circle Test---\n" + // message heading
                (testAllFields() ? "all fields test: passed" : "all fields test: failed") + "\n" +
                (testSetVertices() ? "setVertices test: passed" : "setVertices test: failed") + "\n" +
                (testTranslate() ? "translation test: passed" : "translation test: failed") + "\n" +
                (testRotate() ? "rotate test: passed" : "rotate test: failed") + "\n" +
                (testGetX() ? "getX test: passed" : "getX test: failed") + "\n" +
                (testGetY() ? "getY test: passed" : "getY test: failed") + "\n";

    }
}
