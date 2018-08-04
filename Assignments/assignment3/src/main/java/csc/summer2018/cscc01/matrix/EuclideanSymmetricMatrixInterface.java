package csc.summer2018.cscc01.matrix;

import java.util.List;

/**
 * A square symmetric matrix
 */
public interface EuclideanSymmetricMatrixInterface {

    /**
     * Return a list of points that are the maximum in this matrix
     * @return a list of points that are the maximum in this matrix excluding the diagonal
     */
    public List<Point<Number>> getUpperTriangularMaxPoints ();

    /**
     * Return a list of points that are the minimum in this matrix excluding the diagonal
     * @return a list of points that are the minimums in this matrix excluding the diagonal
     */
    public List<Point<Number>> getUpperTriangularMinPoints ();
}
