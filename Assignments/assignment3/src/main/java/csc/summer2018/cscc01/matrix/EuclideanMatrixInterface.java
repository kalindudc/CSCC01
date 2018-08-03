package csc.summer2018.cscc01.matrix;

import java.util.List;

/**
 * A square symmetric matrix
 */
public interface EuclideanMatrixInterface {

    /**
     * Return a list of points that are the maximum in this matrix
     * @return a list of points that are the maximum in this matrix excluding the diagonal
     */
    public List<Point<Number>> getMaxPoints();

    /**
     * Return a list of points that are the minimum in this matrix excluding the diagonal
     * @return a list of points that are the minimums in this matrix excluding the diagonal
     */
    public List<Point<Number>> getMinPoints();
}
