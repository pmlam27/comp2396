import java.awt.Color;
import java.util.Arrays;

/**
 * This class checks the correctness of the Shape class, and it will also be inherited by other subclass testers.
 */
public class ShapeTester {

    /**
     * the default color is set to black (0, 0, 0).
     */
    final Color defaultColor = new Color(0, 0, 0);
    /**
     * the shape is filled by default.
     */
    final boolean defaultFilled = true;
    /**
     * the shape have no rotation by default.
     */
    final double defaultTheta = 0.0;
    /**
     * the x coordinate of shape center is 10.0 by default.
     */
    final double defaultXc = 10.0;
    /**
     * the y coordinate of shape center is 10.0 by default.
     */
    final double defaultYc = 10.0;

    /**
     * The main function of this class will test all the fields and methods of the Shape class.
     * @param args not used in this application.
     */
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
     * note that xLocal and yLocal will be null. if you want to set its value, you can use the overloaded method.
     * this is designed so that you can customize the values of xLocal and yLocal based on your need.
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
        // set xLocal and yLocal equal to the ones provided.
        shape.xLocal = xLocal;
        shape.yLocal = yLocal;
        return shape;
    }

    /**
     * this method access all the fields of the shape class, see if it matches the default values, and print the result.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to test all fields of subclasses.
     */
    void testAllFields(Shape shape) {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testShape = initializeShapeWithDefault(shape, defaultXLocal, defaultYLocal);

        // the test is successful if all the fields can be accessed and is the same as the default values
        boolean testSuccess =   testShape.color == defaultColor &&
                                testShape.filled == defaultFilled &&
                                testShape.theta == defaultTheta &&
                                testShape.xc == defaultXc &&
                                testShape.yc == defaultYc &&
                                testShape.xLocal == defaultXLocal &&
                                testShape.yLocal == defaultYLocal;

        System.out.println("[" + getClassName(shape) + "] test all fields: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * will translate the shape to see if translate method works, and then print the result.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to test the translate method of subclasses.
     */
    void testTranslate(Shape shape) {
        Shape testShape = initializeShapeWithDefault(shape);
        testShape.translate(100.0, -100.0);

        // the test is successful if the translation result in the correct values.
        boolean testSuccess = testShape.xc == 110.0 && testShape.yc == -90.0;

        System.out.println("[" + getClassName(shape) + "] test translate method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * will rotate the shape to see if rotate method works, and then print the result.
     * @param shape The shape is expected to be uninitialized.
     *              You can provide subclasses of Shape (e.g. Square) to test the rotate method of subclasses.
     */
    void testRotate(Shape shape) {
        Shape testShape = initializeShapeWithDefault(shape);
        testShape.rotate(Math.PI / 4);

        // the test is successful if rotate have successfully changed the theta value.
        boolean testSuccess = testShape.theta == Math.PI / 4;

        System.out.println("[" + getClassName(shape) + "] test rotate method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if calling setVertices would change anything (no fields should be changed).
     * <br>
     * This method should only be used by the ShapeTester class.
     * tester of subclasses should override this method to test for different subclass.
     */
    void testSetVertices() {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testShape = initializeShapeWithDefault(new Shape(), defaultXLocal, defaultYLocal);
        testShape.setVertices(10);

        // the test is successful if setVertices does not change anything.
        boolean testSuccess =   testShape.color == defaultColor &&
                                testShape.filled == defaultFilled &&
                                testShape.theta == defaultTheta &&
                                testShape.xc == defaultXc &&
                                testShape.yc == defaultYc &&
                                testShape.xLocal == defaultXLocal &&
                                testShape.yLocal == defaultYLocal;
        System.out.println("[Shape] test setVertices method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getX can get the correct x values relative to the canvas.
     * <br>
     * This method should only be used by the ShapeTester class.
     * tester of subclasses should override this method to test for different subclass.
     */
    void testGetX() {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testShape = initializeShapeWithDefault(new Shape(), defaultXLocal, defaultYLocal);
        int[] correctXValues = new int[] {20, 20, 0, 0};
        int[] getXResult = testShape.getX();

        // the test is successful if the result of getX is the correct value
        boolean testSuccess = Arrays.equals(correctXValues, getXResult);
        System.out.println("[Shape] test getX method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * check if getY can get the correct y values relative to the canvas.
     * This method should only be used by the ShapeTester class.
     * tester of subclasses should override this method to test for different subclass.
     */
    void testGetY() {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testShape = initializeShapeWithDefault(new Shape(), defaultXLocal, defaultYLocal);
        int[] correctYValues = new int[] {20, 0, 0, 20};
        int[] getYResult = testShape.getY();

        // the test is successful if the result of getY is the correct value
        boolean testSuccess = Arrays.equals(correctYValues, getYResult);
        System.out.println("[Shape] test getY method: " + (testSuccess ? "success" : "failed"));
    }

    /**
     * get the name of the Shape class. It will consider subclasses first, and then the superclass (Shape).
     * @param shape the shape that you want to know the name of.
     * @return a String that is the name of the Shape class.
     */
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
