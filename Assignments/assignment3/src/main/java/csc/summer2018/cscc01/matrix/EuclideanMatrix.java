package csc.summer2018.cscc01.matrix;

import java.util.List;

public interface EuclideanMatrix {

    /**
     * Generate a euclidean distance matrix of this matrix
     * @return a eucidean distance matrix of this matrinx row*row matrix
     */
    public Matrix generateEuclideanDistanceMatrix();

    /**
     * Return a list of points that are the maximum in this matrix
     * @return a list of points that are the maximum in this matrix
     */
    public List<Point<Number>> getMaxPoints();

    /**
     * Return a list of points that are the minimum in this matrix
     * @return a list of points that are the minimums in this matrix
     */
    public List<Point<Number>> getMinPoints();
}
