import java.util.Arrays;

/**
 * This class checks the correctness of the Square class.
 */
public class SquareTester extends ShapeTester {

    /**
     * The main function of this class will test all the fields and methods of the Square class.
     * @param args not used in this application.
     */
    public static void main(String[] args) {
        SquareTester tester = new SquareTester();
        tester.testAllFields(new Square());
        tester.testSetVertices();
        tester.testTranslate(new Square());
        tester.testRotate(new Square());
        tester.testGetX();
        tester.testGetY();
    }

    /**
     * check if calling setVertices would set the xLocal and yLocal values correctly.
     */
    @Override
    void testSetVertices() {
        Shape testSquare = initializeShapeWithDefault(new Square());
        testSquare.setVertices(5.0);
        double[] correctXLocalValue = new double[] {5.0, 5.0, -5.0, -5.0};
        double[] correctYLocalValue = new double[] {5.0, -5.0, -5.0, 5.0};

        // the test is successful if setVertices can set xLocal and yLocal to the correct value.
        boolean testSuccess =   Arrays.equals(correctXLocalValue, testSquare.xLocal) &&
                                Arrays.equals(correctYLocalValue, testSquare.yLocal);

        System.out.println("[Square] test setVertices method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getX can get the correct x values relative to the canvas.
     */
    @Override
    void testGetX() {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testSquare = initializeShapeWithDefault(new Square(), defaultXLocal, defaultYLocal);
        int[] correctXValues = new int[] {20, 20, 0, 0};
        int[] getXResult = testSquare.getX();

        // the test is successful if the result of getX is the correct value.
        boolean testSuccess = Arrays.equals(correctXValues, getXResult);
        System.out.println("[Square] test getX method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getY can get the correct y values relative to the canvas.
     */
    @Override
    void testGetY() {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testSquare = initializeShapeWithDefault(new Square(), defaultXLocal, defaultYLocal);

        int[] correctYValues = new int[] {20, 0, 0, 20};
        int[] getYResult = testSquare.getY();

        // the test is successful if the result of getY is the correct value.
        boolean testSuccess = Arrays.equals(correctYValues, getYResult);
        System.out.println("[Square] test getY method: " + (testSuccess ? "success" : "failed"));
    }
}
