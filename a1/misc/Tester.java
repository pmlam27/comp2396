import java.awt.*;
import java.util.Arrays;

public class Tester {


    public static void main(String[] args) {
        Tester tester = new Tester();
        StringBuilder result = new StringBuilder();
        result.append(tester.squareTest());
        result.append(tester.triangleTest());

        System.out.print("\n---test result---\n");
        System.out.print(result.toString());
    }

    /**
     * @return a string that describe the result
     */
    private String squareTest() {
        System.out.print("\n---square test---\n");
        Square testSquare = this.createSquare();

        System.out.print("before rotation:\n" + ShapePointsToString(testSquare));

        testSquare.rotate(-Math.PI / 4);
        System.out.print("after 45 degree rotation:\n" + ShapePointsToString(testSquare));
        System.out.print("correct values:\n" + "(171, 100), (100, 29), (29, 100), (100, 171)\n");

        // correct points: (171, 100), (100, 29), (29, 100), (100, 171)
        int[] correctX = new int[] {171, 100, 29, 100};
        int[] correctY = new int[] {100, 29, 100, 171};

        boolean rotationCorrect = this.checkPointsEquals(testSquare.getX(), testSquare.getY(), correctX, correctY);

        if(rotationCorrect) {
            return "square test: successful\n";
        } else {
            return "square test: failed\n";
        }
    }

    private String triangleTest() {
        System.out.print("\n---triangle test---\n");
        Triangle testTriangle = this.createTriangle();
        System.out.print(this.ShapePointsToString(testTriangle));
        
        System.out.println(testTriangle.xc);
        System.out.println(testTriangle.yc);
        return "triangle test: placeholder\n";
    }

    private boolean checkPointsEquals(int[] XPointA, int[] YPointA, int[] XPointB, int[] YPointB) {
        boolean noMismatch = true;
        for(int i=0; i<XPointA.length; i++) {
            if(( XPointA[i] != XPointB[i] ) || ( YPointA[i] != YPointB[i] )) {
                noMismatch = false;
                break;
            }
        }
        return noMismatch;
    }
    private String ShapePointsToString(Shape shape) {
        StringBuilder strBuilder = new StringBuilder();
        int[] xCoordinates = shape.getX();
        int[] yCoordinates = shape.getY();

        // assumes that the num of x and y coordinate is the same
        int numOfPoint = shape.getX().length;
        for(int i=0; i<numOfPoint; i++) {
            strBuilder
                    .append("(")
                    .append(xCoordinates[i])
                    .append(", ")
                    .append(yCoordinates[i])
                    .append(")");
            if (i != (numOfPoint-1)) {
                strBuilder.append(", ");
            }
        }
        strBuilder.append("\n");
        return strBuilder.toString();
    }

    private Square createSquare() {
        Square square = new Square();
        square.color = new Color(250, 0, 0);
        square.filled = true;
        square.theta = 0;
        square.xc = 0;
        square.yc = 0;
        square.translate(100, 100);
        square.setVertices(50);
        return square;
    }

    private Triangle createTriangle() {
        // create a triangle
        Triangle triangle = new Triangle();
        triangle.color = new Color(0, 0, 250);
        triangle.filled = true;
        triangle.theta = -Math.PI / 2;
        triangle.xc = 0;
        triangle.yc = 0;
        triangle.translate(400, 100);
        triangle.setVertices(50);
        return triangle;
    }
}
