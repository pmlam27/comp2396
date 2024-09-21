import java.awt.Color;

public class TriangleTester extends MasterTester {
    
    public static void main(String[] args) {
        TriangleTester tester = new TriangleTester();
        System.out.print(tester.testAllAndGetMessage());
    }

    TriangleTester() {
        Triangle triangle = new Triangle();
        triangle.color = new Color(0, 0, 0);
        triangle.filled = true;
        triangle.theta = 0;
        triangle.xc = 0.0;
        triangle.yc = 0.0;
        triangle.xLocal = new double[] {50, -50, -50, 50};
        triangle.yLocal = new double[] {-50, -50, 50, 50};

        this.shapeInstance = triangle;
    }

    @Override
    String testAllAndGetMessage() {

        StringBuilder returnMessage = new StringBuilder();

        // message heading
        returnMessage.append("\n---Triangle Test---\n");

        // test fields
        returnMessage.append(this.testFields() ? "fields test: passed" : "fields test: failed");
        returnMessage.append("\n");

        return returnMessage.toString();
    }
}
