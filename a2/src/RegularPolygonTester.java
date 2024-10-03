public class RegularPolygonTester {

    private RegularPolygon testPolygon;
    public static void main(String[] args) {
        RegularPolygonTester tester = new RegularPolygonTester();
        tester.testSetPolygon();
        System.out.println(tester.testPolygon.contains(0, 5));

        System.out.println();
    }

    void testSetPolygon() {
        this.testPolygon = new RegularPolygon();
    }
}
