import java.awt.Color;
import java.util.Arrays;

/**
 * test the correctness of the RegularPolygon class
 */
public class RegularPolygonTester {

    /**
     * test everything and print debug message
     * @param args not used in this class
     */
    public static void main(String[] args) {
        RegularPolygonTester tester = new RegularPolygonTester();
        tester.testSetterAndGetter();
        tester.testConstructors();
        tester.testContains();
        tester.testTranslate();
        tester.testRotate();
        tester.testGetX();
        tester.testGetY();
    }

    private void testTranslate() {
        RegularPolygon testPolygon = new RegularPolygon();
        testPolygon.setXc(0);
        testPolygon.setYc(0);
        testPolygon.translate(10.0, 10.0);
        boolean testSuccess =   testPolygon.getXc() == 10.0 &&
                                testPolygon.getYc() == 10.0;
        System.out.println("[RegularPolygon] test translate method: " + (testSuccess ? "success" : "failed"));
    }

    private void testRotate() {
        RegularPolygon testPolygon = new RegularPolygon();
        testPolygon.setTheta(0);
        testPolygon.rotate(10.0);
        boolean testSuccess = testPolygon.getTheta() == 10.0;
        System.out.println("[RegularPolygon] test rotate method: " + (testSuccess ? "success" : "failed"));
    }

    private void testGetX() {
        RegularPolygon testPolygon = new RegularPolygon();
        testPolygon.setXc(10.0);
        testPolygon.setYc(10.0);
        testPolygon.setXLocal(new double[] {5.0, -5.0, -5.0});
        testPolygon.setYLocal(new double[] {0.0, 5.0, -5.0});

        boolean testSuccess = Arrays.equals(testPolygon.getX(), new int[] {15, 5, 5});
        System.out.println("[RegularPolygon] test getX method: " + (testSuccess ? "success" : "failed"));
    }

    private void testGetY() {
        RegularPolygon testPolygon = new RegularPolygon();
        testPolygon.setXc(10.0);
        testPolygon.setYc(10.0);
        testPolygon.setXLocal(new double[] {5.0, -5.0, -5.0});
        testPolygon.setYLocal(new double[] {0.0, 5.0, -5.0});

        boolean testSuccess = Arrays.equals(testPolygon.getY(), new int[] {10, 15, 5});
        System.out.println("[RegularPolygon] test getY method: " + (testSuccess ? "success" : "failed"));
    }

    private void testContains() {
        boolean testSuccess = true;

        // xLocal, yLocal, numOfSides, radius are already set in the constructor
        RegularPolygon testPolygon = new RegularPolygon();
        testPolygon.setXc(0);
        testPolygon.setYc(0);
        testPolygon.setColor(Color.blue);
        testPolygon.setFilled(true);
        testPolygon.setTheta(0);

        // the xLocal should be roughly {1, -0.5, -0.5}
        // the yLocal should be roughly {0, 0.86, 0.86}

        if(testPolygon.contains(0, 0) != true) {
            testSuccess = false;
        }

        if(testPolygon.contains(3, 3) != false) {
            testSuccess = false;
        }

        System.out.println("[RegularPolygon] test contains method: " + (testSuccess ? "success" : "failed"));

    }

    private void testConstructors() {
        RegularPolygon testPolygon1 = new RegularPolygon();
        RegularPolygon testPolygon2 = new RegularPolygon(5);
        RegularPolygon testPolygon3 = new RegularPolygon(8, 2.0);

        boolean testSuccess =   testPolygon1.getNumOfSides() == 3 &&
                                testPolygon1.getRadius() == 1.0 &&
                                testPolygon2.getNumOfSides() == 5 &&
                                testPolygon2.getRadius() == 1.0 &&
                                testPolygon3.getNumOfSides() == 8 &&
                                testPolygon3.getRadius() == 2.0;
        System.out.println("[RegularPolygon] test constructors: " + (testSuccess ? "success" : "failed"));
    }
    private void testSetterAndGetter() {
        RegularPolygon testPolygon = new RegularPolygon();
        testPolygon.setXc(0);
        testPolygon.setYc(0);
        testPolygon.setColor(Color.blue);
        testPolygon.setFilled(true);
        testPolygon.setTheta(0);
        testPolygon.setNumOfSides(4);
        testPolygon.setRadius(2);

        boolean testSuccess =   testPolygon.getXc() == 0 &&
                                testPolygon.getYc() == 0 &&
                                testPolygon.getColor() == Color.blue &&
                                testPolygon.getFilled() == true &&
                                testPolygon.getTheta() == 0 &&
                                testPolygon.getNumOfSides() == 4 &&
                                testPolygon.getRadius() == 2;
        System.out.println("[RegularPolygon] test setters and getters: " + (testSuccess ? "success" : "failed"));
    }
}
