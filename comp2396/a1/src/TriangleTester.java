import java.awt.Color;
import java.util.Arrays;

public class TriangleTester {

    public static void main(String[] args) {
        TriangleTester tester = new TriangleTester();
        System.out.print(tester.testAllAndGetMessage());
    }




    String testAllAndGetMessage() {

        StringBuilder returnMessage = new StringBuilder();

        // message heading
        returnMessage.append("\n---Triangle Test---\n");

//        // test fields
//        returnMessage.append(this.testFields() ? "fields test: passed" : "fields test: failed");
//        returnMessage.append("\n");

        return returnMessage.toString();
    }
}
