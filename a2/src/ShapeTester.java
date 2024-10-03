import java.awt.Color;
import java.util.Arrays;

/**
 * test the correctness of the Shape class
 */
public class ShapeTester {

    /**
     * test everything and print debug message
     * @param args not used in this class
     */
    public static void main(String[] args) {
        ShapeTester tester = new ShapeTester();
        tester.testSetterAndGetter();
        tester.testTranslate();
        tester.testRotate();
        tester.testGetX();
        tester.testGetY();
    }

    private void testTranslate() {
        Shape testShape = new Shape();
        testShape.setXc(0);
        testShape.setYc(0);
        testShape.translate(10.0, 10.0);
        boolean testSuccess =   testShape.getXc() == 10.0 &&
                testShape.getYc() == 10.0;
        System.out.println("[Shape] test translate method: " + (testSuccess ? "success" : "failed"));
    }

    private void testRotate() {
        Shape testShape = new Shape();
        testShape.setTheta(0);
        testShape.rotate(10.0);
        boolean testSuccess = testShape.getTheta() == 10.0;
        System.out.println("[Shape] test rotate method: " + (testSuccess ? "success" : "failed"));
    }

    private void testGetX() {
        Shape testShape = new Shape();
        testShape.setXc(10.0);
        testShape.setYc(10.0);
        testShape.setXLocal(new double[] {5.0, -5.0, -5.0});
        testShape.setYLocal(new double[] {0.0, 5.0, -5.0});

        boolean testSuccess = Arrays.equals(testShape.getX(), new int[] {15, 5, 5});
        System.out.println("[Shape] test getX method: " + (testSuccess ? "success" : "failed"));
    }

    private void testGetY() {
        Shape testShape = new Shape();
        testShape.setXc(10.0);
        testShape.setYc(10.0);
        testShape.setXLocal(new double[] {5.0, -5.0, -5.0});
        testShape.setYLocal(new double[] {0.0, 5.0, -5.0});

        boolean testSuccess = Arrays.equals(testShape.getY(), new int[] {10, 15, 5});
        System.out.println("[Shape] test getY method: " + (testSuccess ? "success" : "failed"));
    }

    private void testSetterAndGetter() {
        Shape testShape = new Shape();
        testShape.setXc(0);
        testShape.setYc(0);
        testShape.setColor(Color.blue);
        testShape.setFilled(true);
        testShape.setTheta(0);

        boolean testSuccess =   testShape.getXc() == 0 &&
                                testShape.getYc() == 0 &&
                                testShape.getColor() == Color.blue &&
                                testShape.getFilled() == true &&
                                testShape.getTheta() == 0;
        
        System.out.println("[Shape] test setters and getters: " + (testSuccess ? "success" : "failed"));
    }
}
