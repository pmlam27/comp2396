import java.util.Arrays;

/**
 * This class checks the correctness of the Triangle class.
 */
public class TriangleTester extends ShapeTester {

    /**
     * The main function of this class will test all the fields and methods of the Triangle class.
     * @param args not used in this application.
     */
    public static void main(String[] args) {
        TriangleTester tester = new TriangleTester();
        tester.testAllFields(new Triangle());
        tester.testSetVertices();
        tester.testTranslate(new Triangle());
        tester.testRotate(new Triangle());
        tester.testGetX();
        tester.testGetY();
    }

    /**
     * check if calling setVertices would set the xLocal and yLocal values correctly.
     */
    @Override
    void testSetVertices() {
        Shape testTriangle = initializeShapeWithDefault(new Triangle());
        testTriangle.setVertices(5.0);
        double[] correctXLocalValue = {5.0, -5.0*Triangle.COS_PI_OVER_3, -5.0*Triangle.COS_PI_OVER_3};
        double[] correctYLocalValue = {0, -5.0*Triangle.SIN_PI_OVER_3, 5.0*Triangle.SIN_PI_OVER_3};

        // the test is successful if setVertices can set xLocal and yLocal to the correct value.
        boolean testSuccess =   Arrays.equals(correctXLocalValue, testTriangle.xLocal) &&
                                Arrays.equals(correctYLocalValue, testTriangle.yLocal);
        System.out.println("[triangle] test setVertices method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getX can get the correct x values relative to the canvas.
     */
    @Override
    void testGetX() {
        double[] defaultXLocal = {10.0, -10.0, -10.0};
        double[] defaultYLocal = {0, 10.0, -10.0};
        Shape testTriangle = initializeShapeWithDefault(new Triangle(), defaultXLocal, defaultYLocal);
        int[] correctXValues = new int[] {20, 0, 0};
        int[] getXResult = testTriangle.getX();

        // the test is successful if the result of getX is the correct value.
        boolean testSuccess =  Arrays.equals(correctXValues, getXResult);
        System.out.println("[Triangle] test getX method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getY can get the correct y values relative to the canvas.
     */
    @Override
    void testGetY() {
        double[] defaultXLocal = {10.0, -10.0, -10.0};
        double[] defaultYLocal = {0, 10.0, -10.0};
        Shape testTriangle = initializeShapeWithDefault(new Triangle(), defaultXLocal, defaultYLocal);
        int[] correctYValues = new int[] {10, 20, 0};
        int[] getYResult = testTriangle.getY();

        // the test is successful if the result of getY is the correct value.
        boolean testSuccess =  Arrays.equals(correctYValues, getYResult);
        System.out.println("[Triangle] test getY method: " + (testSuccess ? "success" : "failed"));
    }
}
