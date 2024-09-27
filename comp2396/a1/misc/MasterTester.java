//import java.awt.Color;
//
///**
// * The main method can be used to test all the classes at once.
// * This class will also be inherited by the different tester classes.
// */
//public class MasterTester {
//
//    final Color defaultColor = new Color(0, 0, 0);
//    final boolean defaultFilled = true;
//    final double defaultTheta = 0.0;
//    final double defaultXc = 10.0;
//    final double defaultYc = 10.0;
//
//    public static void main(String[] args) {
//
//
//    }
//
//    /**
//     * will initialize shape with default values in MasterTester.
//     * @param shape The shape is expected to be uninitialized.
//     *              You can provide subclasses of Shape (e.g. Square) to initialize different shapes.
//     * @return the return class will be the same as the one you provided
//     */
//    Shape initializeShapeWithDefault(Shape shape) {
//        shape.color = defaultColor;
//        shape.filled = defaultFilled;
//        shape.theta = defaultTheta;
//        shape.xc = defaultXc;
//        shape.yc = defaultYc;
//        // xLocal and yLocal will be set in test methods, so it is null for now.
//        shape.xLocal = null;
//        shape.yLocal = null;
//        return shape;
//    }
//
//    /**
//     *
//     * @param shape The shape is expected to be uninitialized.
//     *              You can provide subclasses of Shape (e.g. Square) to test different shapes.
//     * @return
//     */
//    boolean testAllFields(Shape shape) {
//        Shape testShape = initializeShapeWithDefault(shape);
//
//        return  testShape.color == defaultColor &&
//                testShape.filled == defaultFilled &&
//                testShape.theta == defaultTheta &&
//                testShape.xc == defaultXc &&
//                testShape.yc == defaultYc &&
//                testShape.xLocal == null &&
//                testShape.yLocal == null;
//    }
//
//    boolean testTranslate(Shape shape) {
//        Shape testShape = initializeShapeWithDefault(shape);
//        testShape.translate(100.0, -100.0);
//        // test if translate have successfully changed the center values.
//        return testShape.xc == 110.0 && testShape.yc == -90.0;
//    }
//
//    boolean testRotate(Shape shape) {
//
//    }
//
//}
