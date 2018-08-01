package csc.summer2018.cscc01;

public class Point {

    private int x;
    private int y;
    private float value;

    /**
     * Create a new instance of a point
     * @param x the x coordinate of this point
     * @param y the y coordinate of this point
     */
    public Point(int x, int y, float value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /**
     * Get the y coordinate of this point
     * @return the y coordinate of this point
     */
    public int getY () {
        return y;
    }

    /**
     * Change the y coordinate of this point
     * @param y the new y coordinate
     */
    public void setY (int y) {
        this.y = y;
    }

    /**
     * Get the x coordinate of this point
     * @return the x coordinate of this point
     */
    public int getX () {
        return x;
    }

    /**
     * Change the x coordinate of this point
     * @param x the new x coordinate
     */
    public void setX (int x) {
        this.x = x;
    }

    /**
     * Get the value of this point
     * @return the value of this point
     */
    public float getValue () {
        return value;
    }

    /**
     * Change the value of this point
     * @param value the new value
     */
    public void setValue (float value) {
        this.value = value;
    }

    /**
     * Return a string representation of this point
     * @return a string representation of this point
     */
    public String toString() {
        return "[(" + x + "," + y + "):" + value + "]";
    }
}
