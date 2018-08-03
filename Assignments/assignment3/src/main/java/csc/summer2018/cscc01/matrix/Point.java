package csc.summer2018.cscc01.matrix;

public class Point<T> {

    private int x;
    private int y;
    private T value;

    /**
     * Create a new instance of a point
     * @param x the x coordinate of this point
     * @param y the y coordinate of this point
     */
    public Point(int x, int y, T value) {
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
    public T getValue () {
        return value;
    }

    /**
     * Change the value of this point
     * @param value the new value
     */
    public void setValue (T value) {
        this.value = value;
    }

    /**
     * Return a string representation of this point
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        return "[(" + x + "," + y + "):" + value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;

        Point<T> other = (Point<T>) obj;

        return x == other.getX() && y == other.getY() && value.equals(other.getValue());
    }
}
