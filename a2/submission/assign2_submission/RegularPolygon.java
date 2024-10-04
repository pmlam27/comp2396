/**
 * this class represent regular polygon, and inherits from the Shape class
 */
public class RegularPolygon extends Shape {
    private int numOfSides;
    private double radius;

    /**
     * construct a regular polygon with custom number of sides and radius
     * @param n number of sides
     * @param r radius of the polygon
     */
    public RegularPolygon(int n, double r) {
        numOfSides = Math.max(n, 3);
        radius = Math.max(r, 0.0);
        this.setVertices();
    }

    /**
     * construct a regular polygon with custom number of sides
     * radius will be 1.0 by default
     * @param n number of sides
     */
    public RegularPolygon(int n) {
        numOfSides = Math.max(n, 3);
        radius = 1.0;
        this.setVertices();
    }

    /**
     * construct a regular polygon with 3 sides and radius of 1.0
     */
    public RegularPolygon() {
        numOfSides = 3;
        radius = 1.0;
        this.setVertices();
    }

    /**
     * get number of sides of the polygon
     * @return number of sides
     */
    public int getNumOfSides() {
        return numOfSides;
    }

    /**
     * get radius of the polygon
     * @return radius of the polygon
     */
    public double getRadius() {
        return radius;
    }

    /**
     * set how many sides the polygon has
     * @param numOfSides the desired number of sides
     */
    public void setNumOfSides(int numOfSides) {
        this.numOfSides = numOfSides;
        this.setVertices();
    }

    /**
     * set the radius of the polygon
     * @param radius the radius of polygon
     */
    public void setRadius(double radius) {
        this.radius = radius;
        this.setVertices();
    }

    /**
     * set the xLocal and yLocal fields using the numOfSides and radius.
     */
    private void setVertices() {
        // all the angles are relative to center.

        // the difference in angle between each vertex.
        double angleDelta = 2*Math.PI / numOfSides;

        // vertexAngle = 0 points towards the positive x-axis, and counter-clockwise is positive (polar coordinate)
        // check if the numOfSides is even,
        // if it's even then set to angle delta/2, otherwise set to 0
        double vertexAngle = numOfSides%2 == 0 ? angleDelta/2 : 0;

        double[] xLocalValues = new double[numOfSides];
        double[] yLocalValues = new double[numOfSides];

        for (int i=0; i<numOfSides; i++) {
            // calculate the coordinate based on the radius and the vertexAngle (polar to cartesian)
            xLocalValues[i] = radius * Math.cos(vertexAngle);
            yLocalValues[i] = radius * Math.sin(vertexAngle);

            vertexAngle += angleDelta;
        }

        this.setXLocal(xLocalValues);
        this.setYLocal(yLocalValues);
    }

    /**
     * determines whether the given point is inside the polygon or not.
     * @param x the x coordinate of the given point
     * @param y the y coordinate of the given point
     */
    public boolean contains(double x, double y) {
        //  uses my own algorithm to determine whether a point X is contained in the polygon
        //  for each point A, obtain the next point B in counter-clockwise direction
        //  get the vectorAB, vectorAC (A to center), and vectorAX
        //  get the magnitude of cross products (vectorAB x vectorAC), (vectorAB x vectorAX)
        //  if the magnitude of the two cross products are both positive or negative, then C and X are on the same side of AB
        //  if C and X are on the same side for all sides of the polygon, then X is inside the polygon
        //
        //  (note: the C is always inside the polygon since the polygon should be convex polygon)

        int verticesCount = this.getXLocal().length;

        // true by default, if any side detects that X is on different side from C, then it will become false
        boolean isContainedInPolygon = true;
        for(int i = 0; i<verticesCount; i++) {
            // assumes that the values of xLocal and yLocal are inserted in counter-clockwise order
            double[] A = {getXLocal()[i], getYLocal()[i], 0};
            double[] B;
            // get the first point as pointB if pointA is already the last point
            if (i == verticesCount-1) {
                B = new double[] {getXLocal()[0], getYLocal()[0], 0};
            } else {
                B = new double[] {getXLocal()[i+1], getYLocal()[i+1], 0};
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
