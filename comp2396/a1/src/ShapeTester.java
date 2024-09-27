import java.awt.Color;
import java.util.Arrays;

/**
 * This class checks the correctness of the Shape class,
 * and it will also be inherited by other subclass Testers.
 */
public class ShapeTester {

    final Color defaultColor = new Color(0, 0, 0);
    final boolean defaultFilled = true;
    final double defaultTheta = 0.0;
    final double defaultXc = 10.0;
    final double defaultYc = 10.0;

    public static void main(String[] args) {
        ShapeTester tester = new ShapeTester();
        tester.testAllFields(new Shape());
        tester.testSetVertices();
        tester.testTranslate(new Shape());
        tester.testRotate(new Shape());
        tester.testGetX();
        tester.testGetY();

    }

    /**
     * will initialize shape with default values in MasterTester.
     * note that xLocal and yLocal will be null.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to initialize different shapes.
     * @return class with the initialized value.
     */
    Shape initializeShapeWithDefault(Shape shape) {
        shape.color = defaultColor;
        shape.filled = defaultFilled;
        shape.theta = defaultTheta;
        shape.xc = defaultXc;
        shape.yc = defaultYc;
        // xLocal and yLocal will be set in test methods, so it is null for now.
        shape.xLocal = null;
        shape.yLocal = null;
        return shape;
    }

    /**
     * will initialize shape with default values in MasterTester, plus the values you provided.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to initialize different shapes.
     * @param xLocal set the xLocal field of the Shape class
     * @param yLocal set the yLocal field of the Shape class
     * @return class with the initialized value.
     */
    Shape initializeShapeWithDefault(Shape shape, double[] xLocal, double[] yLocal) {
        shape.color = defaultColor;
        shape.filled = defaultFilled;
        shape.theta = defaultTheta;
        shape.xc = defaultXc;
        shape.yc = defaultYc;
        // xLocal and yLocal will be set in test methods, so it is null for now.
        shape.xLocal = xLocal;
        shape.yLocal = yLocal;
        return shape;
    }

    /**
     * will access all the fields of the shape class and see if it matches the default values.
     * this method will print whether the test is successful or not.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to test all fields of subclasses.
     */
    void testAllFields(Shape shape) {
        Shape testShape = initializeShapeWithDefault(shape);

        boolean testAllFieldsSuccess =  testShape.color == defaultColor &&
                                        testShape.filled == defaultFilled &&
                                        testShape.theta == defaultTheta &&
                                        testShape.xc == defaultXc &&
                                        testShape.yc == defaultYc &&
                                        testShape.xLocal == null &&
                                        testShape.yLocal == null;

        System.out.println("[" + getClassName(shape) + "] test all fields: " + (testAllFieldsSuccess ? "success" : "failed"));
    }

    /**
     * will translate the shape to see if translate method works.
     * this method will print whether the test is successful or not.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to test all fields of subclasses.
     */
    void testTranslate(Shape shape) {
        Shape testShape = initializeShapeWithDefault(shape);
        testShape.translate(100.0, -100.0);

        // test if translate have successfully changed the center values.
        boolean testTranslateSuccess = testShape.xc == 110.0 && testShape.yc == -90.0;

        System.out.println("[" + getClassName(shape) + "] test translate method: " + (testTranslateSuccess ? "success" : "failed"));
    }

    /**
     * will rotate the shape to see if rotate method works.
     * this method will print whether the test is successful or not.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to test all fields of subclasses.
     */
    void testRotate(Shape shape) {
        Shape testShape = initializeShapeWithDefault(shape);
        testShape.rotate(Math.PI / 4);

        // test if rotate have successfully changed the theta value.
        boolean testRotateSuccess = testShape.theta == Math.PI / 4;

        System.out.println("[" + getClassName(shape) + "] test rotate method: " + (testRotateSuccess ? "success" : "failed"));
    }

    /**
     * This method should only be used by the ShapeTester class.
     * tester of subclasses should override this method to test for different subclass.
     */
    void testSetVertices() {
        Shape testShape = initializeShapeWithDefault(new Shape());
        testShape.setVertices(10);

        boolean testSuccess =    testShape.color == defaultColor &&
                                            testShape.filled == defaultFilled &&
                                            testShape.theta == defaultTheta &&
                                            testShape.xc == defaultXc &&
                                            testShape.yc == defaultYc &&
                                            testShape.xLocal == null &&
                                            testShape.yLocal == null;
        System.out.println("[Shape] test setVertices method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * This method should only be used by the ShapeTester class.
     * tester of subclasses should override this method to test for different subclass.
     */
    void testGetX() {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testShape = initializeShapeWithDefault(new Shape(), defaultXLocal, defaultYLocal);
        int[] supposedXValues = new int[] {20, 20, 0, 0 };
        int[] getXResult = testShape.getX();

        boolean testSuccess = Arrays.equals(supposedXValues, getXResult);
        System.out.println("[Shape] test getX method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * This method should only be used by the ShapeTester class.
     * tester of subclasses should override this method to test for different subclass.
     */
    void testGetY() {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testShape = initializeShapeWithDefault(new Shape(), defaultXLocal, defaultYLocal);
        int[] supposedYValues = new int[] {20, 0, 0, 20};
        int[] getYResult = testShape.getY();

        boolean testSuccess = Arrays.equals(supposedYValues, getYResult);
        System.out.println("[Shape] test getY method: " + (testSuccess ? "success" : "failed"));
    }

    String getClassName(Shape shape) {
        if (shape instanceof Square) {
            return "Square";
        } else if (shape instanceof Circle) {
            return "Circle";
        } else if (shape instanceof Triangle) {
            return "Triangle";
        } else {
            return "Shape";
        }
    }

}
