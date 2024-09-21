import java.awt.Color;

/**
 * This class checks the correctness of the Shape class
 */
public class ShapeTester extends MasterTester {

    public static void main(String[] args) {
        ShapeTester tester = new ShapeTester();
        System.out.print(tester.testAllAndGetMessage());
    }

    /**
     * a constuctor to set the shapeInstance field so that it can be tested.
     */
    ShapeTester() {
        Shape shape = new Shape();
        shape.color = new Color(0, 0, 0);
        shape.filled = true;
        shape.theta = 0;
        shape.xc = 0.0;
        shape.yc = 0.0;
        shape.xLocal = new double[] {50, -50, -50, 50};
        shape.yLocal = new double[] {-50, -50, 50, 50};

        this.shapeInstance = shape;
    }

    /**
     * test everything that should be tested.
     * the setVertices method is not tested because it will be overridden in subclasses.
     * @return a string that should be printed to indicate whether the tests are successful or not.
     */
    @Override
    String testAllAndGetMessage() {

        StringBuilder returnMessage = new StringBuilder();

        // message heading
        returnMessage.append("\n---Shape Test---\n");

        // test fields
        returnMessage.append(this.testFields() ? "fields test: passed" : "fields test: failed").append("\n");
        
        // test translation
        shapeInstance.translate(100.0, -100.0);
        boolean translateSuccess = shapeInstance.xc == 100.0 && shapeInstance.yc == -100.0;
        returnMessage.append(translateSuccess ? "translation test: passed" : "translation test: failed").append("\n");

        // test rotate
        shapeInstance.rotate(Math.PI / 4);
        boolean rotateSuccess = shapeInstance.theta == Math.PI / 4;
        returnMessage.append(rotateSuccess ? "rotate test: passed" : "rotate test: failed").append("\n");

        // returns the accumulated messages
        return returnMessage.toString();
    }
}
