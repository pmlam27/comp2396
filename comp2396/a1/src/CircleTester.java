import java.awt.Color;

/**
 * This class checks the correctness of the Shape class
 */
public class CircleTester extends MasterTester {

    public static void main(String[] args) {
        CircleTester tester = new CircleTester();
        System.out.print(tester.testAllAndGetMessage());
    }

    CircleTester() {
        Circle circle = new Circle();
        circle.color = new Color(0, 0, 0);
        circle.filled = true;
        circle.theta = 0;
        circle.xc = 0.0;
        circle.yc = 0.0;
        circle.xLocal = new double[] {50, -50, -50, 50};
        circle.yLocal = new double[] {-50, -50, 50, 50};

        this.shapeInstance = circle;
    }

    @Override
    String testAllAndGetMessage() {

        StringBuilder returnMessage = new StringBuilder();

        // message heading
        returnMessage.append("\n---Circle Test---\n");

        // test fields
        returnMessage.append(this.testFields() ? "fields test: passed" : "fields test: failed");
        returnMessage.append("\n");

        return returnMessage.toString();
    }
}
