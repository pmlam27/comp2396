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

    public boolean contains() {
        return false;
    }
}
