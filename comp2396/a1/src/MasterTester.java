import java.awt.Color;

/**
 * The main method can be used to test all the classes at once.
 * This class will also be inherited by the different tester classes.
 */
abstract public class MasterTester {

    Shape shapeInstance;
    public static void main(String[] args) {
        ShapeTester shapeTester = new ShapeTester();
        SquareTester squareTester = new SquareTester();
        CircleTester circleTester = new CircleTester();
        TriangleTester triangleTester = new TriangleTester();

        System.out.print(shapeTester.testAllAndGetMessage());
        System.out.print(squareTester.testAllAndGetMessage());
        System.out.print(circleTester.testAllAndGetMessage());
        System.out.print(triangleTester.testAllAndGetMessage());
    }

    abstract String testAllAndGetMessage();

    boolean testFields() {
        Color color = shapeInstance.color;
        boolean filled = shapeInstance.filled;
        double theta = shapeInstance.theta;
        double xc = shapeInstance.xc;
        double yc = shapeInstance.yc;
        double[] xLocal = shapeInstance.xLocal;
        double[] yLocal = shapeInstance.yLocal;

        // primitive values cannot be null
        if (color != null || xLocal != null || yLocal != null) {
            // test passed
            return true;
        } else {
            // test failed
            return false;
        }
    }
}
