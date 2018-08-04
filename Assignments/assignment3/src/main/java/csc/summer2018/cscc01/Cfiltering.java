package csc.summer2018.cscc01;

import csc.summer2018.cscc01.matrix.EucledianSymmetricMatrix;
import csc.summer2018.cscc01.matrix.Matrix;
import csc.summer2018.cscc01.matrix.Point;

import java.text.DecimalFormat;
import java.util.List;

public class Cfiltering {

    // this is a 2d matrix i.e. user*movie
    private Matrix userMovieMatrix;
    // this is a 2d matrix i.e. user*user
    private EucledianSymmetricMatrix userUserMatrix;
    // create a decimal format instance
    // to get all the values of the userUserMatrix in 4 decimal places
    private DecimalFormat df4 = new DecimalFormat("0.0000");

    /**
     * Default Constructor.
     */
    public Cfiltering() {
        // this is 2d matrix of size 1*1
        userMovieMatrix = new Matrix(1, 1);
        // this is 2d matrix of size 1*1
        userUserMatrix = new EucledianSymmetricMatrix(1);
    }

    /**
     * Constructs an object which contains two 2d matrices, one of size
     * users*movies which will store integer movie ratings and one of size
     * users*users which will store float similarity scores between pairs of
     * users.
     *
     * @param numberOfUsers Determines size of matrix variables.
     * @param numberOfMovies Determines size of matrix variables.
     */
    public Cfiltering(int numberOfUsers, int numberOfMovies) {
        // this is a 2d matrix of size numberOfUsers*numberOfMovies
        userMovieMatrix = new Matrix(numberOfUsers, numberOfMovies);
        // this is a 2d matrix of size numberOfUsers*numberOfUsers
        userUserMatrix = new EucledianSymmetricMatrix(numberOfUsers);
    }

    /**
     * The purpose of this method is to populate the UserMovieMatrix. As input
     * parameters it takes in a rowNumber, columnNumber and a rating value. The
     * rating value is then inserted in the UserMovieMatrix at the specified
     * rowNumber and the columnNumber.
     *
     * @param rowNumber The row number of the userMovieMatrix.
     * @param columnNumber The column number of the userMovieMatrix.
     * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
     */
    public void populateUserMovieMatrix(int rowNumber, int columnNumber,
                                        int ratingValue) {
        userMovieMatrix.addPoint(new Point<Number>(rowNumber, columnNumber, ratingValue));
    }

    /**
     * Determines how similar each pair of users is based on their ratings. This
     * similarity value is represented with with a float value between 0 and 1,
     * where 1 is perfect/identical similarity. Stores these values in the
     * userUserMatrix.
     */
    public void calculateSimilarityScore() {
        userUserMatrix = userMovieMatrix.generateEuclideanDistanceMatrix();
    }

    /**
     * Returns the user * user matrix
     * @return the user * user matrix
     */
    public EucledianSymmetricMatrix getUserUserMatrix() {
        return userUserMatrix;
    }

    /**
     * Get the most similar points in the user * user matrix
     * @return the most similar points in the user * user matrix
     */
    public List<Point<Number>> getMostSimilarPairOfUsers() {
        return userUserMatrix.getUpperTriangularMaxPoints();
    }

    /**
     * Get the most dissimilar points in the user * user matrix
     * @return the most dissimilar points in the user * user matrix
     */
    public List<Point<Number>> getMostDisimilarPairOfUsers() {
        return userUserMatrix.getUpperTriangularMinPoints();
    }
}
