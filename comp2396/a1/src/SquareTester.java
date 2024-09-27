import java.awt.Color;
import java.util.Arrays;

public class SquareTester extends ShapeTester {

    public static void main(String[] args) {
        SquareTester tester = new SquareTester();
        tester.testAllFields(new Square());
        tester.testTranslate(new Square());
        tester.testRotate(new Square());
    }

    /**
     * test the set vertices method
     * @return
     */
    boolean testSetVertices(Square square) {

        System.out.println("square test set vertices called");
        Shape testSquare = initializeShapeWithDefault(square);
        testSquare.setVertices(5.0);
        double[] supposedXLocalValue = new double[] {5.0, 5.0, -5.0, -5.0};
        double[] supposedYLocalValue = new double[] {5.0, -5.0, -5.0, 5.0};

        return  Arrays.equals(supposedXLocalValue, testSquare.xLocal) &&
                Arrays.equals(supposedYLocalValue, testSquare.yLocal);
    }

    boolean testGetX(Square square) {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testSquare = initializeShapeWithDefault(square, defaultXLocal, defaultYLocal);

        int[] supposedXValues = new int[] {20, 20, 0, 0};
        int[] getXResult = testSquare.getX();
        return Arrays.equals(supposedXValues, getXResult);
    }

    boolean testGetY(Square square) {
        double[] defaultXLocal = {10.0, 10.0, -10.0, -10.0};
        double[] defaultYLocal = {10.0, -10.0, -10.0, 10.0};
        Shape testSquare = initializeShapeWithDefault(square, defaultXLocal, defaultYLocal);

        int[] supposedYValues = new int[] {20, 20, 0, 0};
        int[] getYResult = testSquare.getY();
        return Arrays.equals(supposedYValues, getYResult);
    }

//    /**
//     * test everything and return a message
//     * @return a string that should be printed to indicate whether the tests are successful or not.
//     */
//    String testAllAndGetMessage() {
//
//        return  "\n---Square Test---\n" + // message heading
//                (testAllFields(new Square()) ? "all fields test: passed" : "all fields test: failed") + "\n" +
//                (testSetVertices(new Square()) ? "setVertices test: passed" : "setVertices test: failed") + "\n" +
//                (testTranslate(new Square()) ? "translation test: passed" : "translation test: failed") + "\n" +
//                (testRotate(new Square()) ? "rotate test: passed" : "rotate test: failed") + "\n" +
//                (testGetX(new Square()) ? "getX test: passed" : "getX test: failed") + "\n" +
//                (testGetY(new Square()) ? "getY test: passed" : "getY test: failed") + "\n";
//    }
}
