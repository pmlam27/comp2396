public class RegularPolygon extends Shape {
    private int numOfSides;
    private double radius;

    public RegularPolygon(int n, double r) {
        numOfSides = Math.max(n, 3);
        radius = Math.max(r, 0.0);
        this.setVertices();
    }

    public RegularPolygon(int n) {
        numOfSides = Math.max(n, 3);
        radius = 1.0;
        this.setVertices();
    }

    public RegularPolygon() {
        numOfSides = 3;
        radius = 1.0;
        this.setVertices();
    }

    public int getNumOfSides() {
        return numOfSides;
    }

    public double getRadius() {
        return radius;
    }

    public void setNumOfSides(int numOfSides) {
        this.numOfSides = numOfSides;
        this.setVertices();
    }

    public void setRadius(double radius) {
        this.radius = radius;
        this.setVertices();
    }

    private void setVertices() {
        // all the angles are relative to center.

        // the difference in angle between each vertex.
        double angleDelta = 2*Math.PI / numOfSides;

        // vertexAngle = 0 points towards the positive x-axis, and counter-clockwise is positive
        // check if the numOfSides is even,
        // if it's even then set to angle delta/2, otherwise set to 0
        double vertexAngle = numOfSides%2 == 0 ? angleDelta/2 : 0;

        double[] xLocalValues = new double[numOfSides];
        double[] yLocalValues = new double[numOfSides];

        for (int i=0; i<numOfSides; i++) {
            xLocalValues[i] = radius * Math.cos(vertexAngle);
            yLocalValues[i] = radius * Math.sin(vertexAngle);

            vertexAngle += angleDelta;
        }

        this.setXLocal(xLocalValues);
        this.setYLocal(yLocalValues);
    }

    /**
     * uses my own algorithm to determine whether a point X is contained in the polygon
     * ->   for each point A, obtain the next point B in counter-clockwise direction
     * ->   get the vectorAB, vectorAC (A to center), and vectorAX
     * ->   get the magnitude of cross products (vectorAB x vectorAC), (vectorAB x vectorAX)
     * ->   if the magnitude of the two cross products are both positive or negative, then C and X are on the same side of AB
     * ->   if C and X are on the same side for all sides of the polygon, then X is inside the polygon
     *
     * (note: the C is always inside the polygon since the polygon should be convex polygon)
     * @return
     */
    public boolean contains(double x, double y) {
        int verticesCount = this.getxLocal().length;

        // true by default, if any side detects that X is on different side from C, then it will become false
        boolean isContainedInPolygon = true;
        for(int i = 0; i<verticesCount; i++) {
            // assumes that the values of xLocal and yLocal are inserted in counter-clockwise order
            double[] A = {getxLocal()[i], getyLocal()[i], 0};
            double[] B;
            // get the first point as pointB if pointA is already the last point
            if (i == verticesCount-1) {
                B = new double[] {getxLocal()[0], getyLocal()[0], 0};
            } else {
                B = new double[] {getxLocal()[i+1], getyLocal()[i+1], 0};
            }
            // assumes C is the origin
            double[] vectorAB = {B[0]-A[0], B[1]-A[1], 0};
            double[] vectorAC = {-A[0], -A[1], 0};
            double[] vectorAX = {x-A[0], y-A[1], 0};

            // for any vector <x1, y1, 0>, <x2, y2, 0>, magnitude of cross product (in the k direction) is x1*y2 - x2*y1
            double magnitudeABCrossAX = vectorAB[0]*vectorAX[1] - vectorAB[1]*vectorAX[0];
            double magnitudeABCrossAC = vectorAB[0]*vectorAC[1] - vectorAB[1]*vectorAC[0];

            // if the two magnitude have different sign, their product will be negative
            if(magnitudeABCrossAC * magnitudeABCrossAX < 0) {
                isContainedInPolygon = false;
            }
            // for the case where their product equals to 0, it means X lies on the vertices or the segment, which is also considered as passed.
        }
        return isContainedInPolygon;
    }
}
