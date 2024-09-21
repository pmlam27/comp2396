import java.awt.Color;

public class SquareTester extends MasterTester {
    
    public static void main(String[] args) {
        SquareTester tester = new SquareTester();
        System.out.print(tester.testAllAndGetMessage());
    }

    SquareTester() {
        Square square = new Square();
        square.color = new Color(0, 0, 0);
        square.filled = true;
        square.theta = 0;
        square.xc = 0.0;
        square.yc = 0.0;
        square.xLocal = new double[] {50, -50, -50, 50};
        square.yLocal = new double[] {-50, -50, 50, 50};

        this.shapeInstance = square;
    }

    @Override
    String testAllAndGetMessage() {

        StringBuilder returnMessage = new StringBuilder();

        // message heading
        returnMessage.append("\n---Square Test---\n");

        // test fields
        returnMessage.append(this.testFields() ? "fields test: passed" : "fields test: failed");
        returnMessage.append("\n");

        return returnMessage.toString();
    }
}
