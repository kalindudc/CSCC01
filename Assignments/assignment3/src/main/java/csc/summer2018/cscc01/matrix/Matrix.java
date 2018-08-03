package csc.summer2018.cscc01.matrix;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0000");

    private int numColumns;
    private int numRows;
    private Point<Number>[][] matrix;

    public Matrix (int numRows, int numColumns) {
        this.numColumns = numColumns;
        this.numRows = numRows;
        this.matrix = new Point[numRows][numColumns];
        generateEmpty();
    }

    private void generateEmpty() {
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                matrix[x][y] = new Point<Number>(x, y, 0);
            }
        }
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
    public void addPoint(Point<Number> p) {
        matrix[p.getX()][p.getY()] = p;
    }

    /**
     * Get a point from this matrix
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return the point at (x,y)
     */
    public Point<Number> getPoint(int x, int y) {
        return matrix[x][y];
    }

    /**
     * Generate a euclidean distance matrix of this matrix
     * @return a eucidean distance matrix of this matrinx row*row matrix
     */
    public EucledianMatrix generateEuclideanDistanceMatrix() {
        EucledianMatrix distanceMatrix = new EucledianMatrix(numRows);
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numRows; y++) {
                int distancePower = 0;
                for (int inner = 0; inner < numColumns; inner++) {
                    distancePower += Math.pow((matrix[x][inner].getValue().floatValue() - matrix[y][inner].getValue().floatValue()), 2);
                }
                distanceMatrix.addPoint(new Point(x, y, (float) (1/(1+Math.sqrt(distancePower)))));
            }

        }

        return distanceMatrix;
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

    @Override
    public boolean equals (Object o) {
        if (!(o instanceof Matrix)) return false;

        Matrix other = (Matrix) o;
        if (other.getNumRows() != numRows || other.getNumColumns() != numColumns) return false;

        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numColumns; y++) {
                if (!matrix[x][y].equals(other.getPoint(x,y))) return false;
            }
        }
        return true;
    }
}
