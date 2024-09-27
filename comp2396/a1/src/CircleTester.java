import java.util.Arrays;

/**
 * This class checks the correctness of the Circle class.
 */
public class CircleTester extends ShapeTester {

    /**
     * The main function of this class will test all the fields and methods of the Circle class.
     * @param args not used in this application.
     */
    public static void main(String[] args) {
        CircleTester tester = new CircleTester();
        tester.testAllFields(new Circle());
        tester.testSetVertices();
        tester.testTranslate(new Circle());
        tester.testRotate(new Circle());
        tester.testGetX();
        tester.testGetY();
    }

    /**
     * check if calling setVertices would set the xLocal and yLocal values correctly.
     */
    @Override
    void testSetVertices() {
        Shape testCircle = initializeShapeWithDefault(new Circle());
        testCircle.setVertices(5.0);
        double[] correctXLocalValue = {-5.0, 5.0};
        double[] correctYLocalValue = {-5.0, 5.0};

        // the test is successful if setVertices can set xLocal and yLocal to the correct value.
        boolean testSuccess =   Arrays.equals(correctXLocalValue, testCircle.xLocal) &&
                                Arrays.equals(correctYLocalValue, testCircle.yLocal);

        System.out.println("[Circle] test setVertices method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getX can get the correct x values relative to the canvas.
     */
    @Override
    void testGetX() {
        double[] defaultXLocal = {-10.0, 10.0};
        double[] defaultYLocal = {-10.0, 10.0};
        Shape testCircle = initializeShapeWithDefault(new Circle(), defaultXLocal, defaultYLocal);
        int[] correctXValues = new int[] {0, 20};
        int[] getXResult = testCircle.getX();

        // the test is successful if the result of getX is the correct value.
        boolean testSuccess = Arrays.equals(correctXValues, getXResult);
        System.out.println("[Circle] test getX method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getY can get the correct y values relative to the canvas.
     */
    @Override
    void testGetY() {
        double[] defaultXLocal = {-10.0, 10.0};
        double[] defaultYLocal = {-10.0, 10.0};
        Shape testCircle = initializeShapeWithDefault(new Circle(), defaultXLocal, defaultYLocal);
        int[] correctYValues = new int[] {0, 20};
        int[] getYResult = testCircle.getY();

        // the test is successful if the result of getY is the correct value.
        boolean testSuccess = Arrays.equals(correctYValues, getYResult);
        System.out.println("[Circle] test getY method: " + (testSuccess ? "success" : "failed"));
    }
}
