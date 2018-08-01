package csc.summer2018.cscc01;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0000");

    private int numColumns;
    private int numRows;
    private Point[][] matrix;

    /**
     * A builder for this matrix
     */
    public static class Builder{

        private int numColumns;
        private int numRows;

        /**
         * Create a new matric builder
         */
        public Builder() {
            this.numColumns = 0;
            this.numRows = 0;
        }

        /**
         * Change the number of columns
         * @param numColumns the new number of columns
         * @return a builder with the new number of columns
         */
        public Builder columns(int numColumns) {
            this.numColumns = numColumns;
            return this;
        }

        /**
         * Change the number of rows
         * @param numRows the new number of rows
         * @return a builder with the new number of rows
         */
        public Builder rows(int numRows) {
            this.numRows = numRows;
            return this;
        }

        /**
         * Generate a matrix using this bulder
         * @return a mtrix built out of this builder
         */
        public Matrix build() {
            return new Matrix(this);
        }
    }

    private Matrix(Builder b) {
        this.numColumns = b.numColumns;
        this.numRows = b.numRows;
        this.matrix = new Point[numRows][numColumns];
    }

    /**
     * Get the number of rows in this matrix
     * @return the number of rows in this matrix
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Get the number of columns in this matrix
     * @return the number of columns in this matrix
     */
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Add a point to this matrix
     * @param p the point to add
     */
    public void addPoint(Point p) {
        this.matrix[p.getX()][p.getY()] = p;
    }

    /**
     * Get a point from this matrix
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return the point at (x,y)
     */
    public Point getPoint(int x, int y) {
        return matrix[x][y];
    }

    /**
     * Generate a euclidean distance matrix of this matrix
     * @return a eucidean distance matrix of this matrinx row*row matrix
     */
    public Matrix generateEuclideanDistanceMatrix() {

        Matrix distanceMatrix = new Matrix.Builder().rows(numRows).columns(numRows).build();
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numRows; y++) {
                int distancePower = 0;
                for (int inner = 0; inner < numColumns; inner++) {
                    distancePower += Math.pow((matrix[x][inner].getValue() - matrix[y][inner].getValue()), 2);
                }
                distanceMatrix.addPoint(new Point(x, y, (float) (1/(1+Math.sqrt(distancePower)))));
            }

        }

        return distanceMatrix;
    }

    public List<Point> getMaxPoints() {
        List<Point> res = new ArrayList<Point>();

        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numRows; y++) {

            }
        }
        return res;
    }

    /**
     * Return a string representation of this matrix
     * @return a string representation of this matrix
     */
    public String toString() {
        String str = "";

        for (int i = 0; i < numRows; i++) {
            str += "[";
            for (int j = 0; j < numColumns; j++) {
                str += DECIMAL_FORMAT.format(this.matrix[i][j].getValue()) + ",";
            }
            str = str.substring(0, str.length() - 1);
            str += "]\n";
        }

        return str.substring(0, str.length() - 1);
    }

}
