package csc.summer2018.cscc01;

import java.text.DecimalFormat;

public class Cfiltering {

    // this is a 2d matrix i.e. user*movie
    private Matrix userMovieMatrix;
    // this is a 2d matrix i.e. user*user
    private Matrix userUserMatrix;
    // create a decimal format instance
    // to get all the values of the userUserMatrix in 4 decimal places
    private DecimalFormat df4 = new DecimalFormat("0.0000");
    
    /**
     * Default Constructor.
     */
    public Cfiltering() {
        // this is 2d matrix of size 1*1
        userMovieMatrix = new Matrix.Builder().columns(1).rows(1).build();
        // this is 2d matrix of size 1*1
        userUserMatrix = new Matrix.Builder().columns(1).rows(1).build();
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
        userMovieMatrix = new Matrix.Builder().columns(numberOfMovies).rows(numberOfUsers).build();
        // this is a 2d matrix of size numberOfUsers*numberOfUsers
        userUserMatrix = new Matrix.Builder().columns(numberOfUsers).rows(numberOfUsers).build();
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
        userMovieMatrix.addPoint(new Point(rowNumber, columnNumber, ratingValue));
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
     * Prints out the similarity scores of the userUserMatrix, with each row and
     * column representing each/single user and the cell position (i,j)
     * representing the similarity score between user i and user j.
     */
    
    public void printUserUserMatrix() {
        System.out.println(userUserMatrix);
    }

    /**
     * This function finds and prints the most similar pair of users in the
     * userUserMatrix.
     */
    public void findAndprintMostSimilarPairOfUsers() {
        // set the highest similarity score to 0 since 0 is the minimum score
        float highest_sim_score = 0;
        // initiate two variables to store the first two most similar users
        int similar_user1 = 0;
        int similar_user2 = 0;
        // loop through the userUserMatrix to access all the similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                // when the users are not the same
                if (row != col) {
                    // if the similarity_score is higher than the previous highest
                    // similarity score
                    if (userUserMatrix[row][col] > highest_sim_score) {
                        // set the similarity_score to the highest similarity score
                        highest_sim_score = userUserMatrix[row][col];
                        // save the first two most similar users
                        similar_user1 = row;
                        similar_user2 = col;
                    }
                }
            }
        }
        // print the first two users with the highest similarity score
        System.out.println(
                           "The most similar pairs of users from above userUserMatrix are: ");
        System.out.print(
                         "User" + (similar_user1 + 1) + " and " + "User" + (similar_user2 + 1));
        // loop through the userUserMatrix to check which other users have the
        // highest similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                if (userUserMatrix[row][col] == highest_sim_score) {
                    if (row != col) {
                        // print the users in the upper triangle of the matrix
                        // and when they are not the first two most similar users
                        if ((col > row) && (row != similar_user1 || col != similar_user2)) {
                            // print a comma
                            System.out.print(",");
                            // print a newline
                            System.out.print("\n");
                            // print the other most similar pair of users
                            System.out
                            .print("User" + (row + 1) + " and " + "User" + (col + 1));
                        }
                    }
                }
            }
        }
        // print the highest similarity score
        System.out.println(
                           "\n" + "with similarity score of " + df4.format(highest_sim_score));
    }
    
    /*
     * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
     * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
     */
    /**
     * This function finds and prints the most dissimilar pair of users in the
     * userUserMatrix.
     *
     * @param COMPLETE THIS IF NEEDED
     * @param COMPLETE THIS IF NEEDED
     * @return COMPLETE THIS IF NEEDED
     */
    public void findAndprintMostDissimilarPairOfUsers() {
        // set the lowest similarity score to 2 since 2 > 1
        float lowest_sim_score = 2;
        // initiate two variables to store the first two most dissimilar users
        int dissimilar_user1 = 0;
        int dissimilar_user2 = 0;
        // loop through the userUserMatrix to access all the similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                // when the users are not the same
                if (row != col) {
                    // if the similarity_score is lower than the previous lowest
                    // similarity score
                    if (userUserMatrix[row][col] < lowest_sim_score) {
                        // set the similarity_score to the lowest similarity score
                        lowest_sim_score = userUserMatrix[row][col];
                        // save the first two most similar users
                        dissimilar_user1 = row;
                        dissimilar_user2 = col;
                    }
                }
            }
        }
        // print the first two users with the lowest similarity score
        System.out.println(
                           "The most dissimilar pairs of users from above userUserMatrix are: ");
        System.out.print("User" + (dissimilar_user1 + 1) + " and " + "User"
                         + (dissimilar_user2 + 1));
        // loop through the userUserMatrix to check which other users have the
        // lowest similarity scores
        for (int row = 0; row < userUserMatrix.length; row++) {
            for (int col = 0; col < userUserMatrix[row].length; col++) {
                if (userUserMatrix[row][col] == lowest_sim_score) {
                    if (row != col) {
                        // print the users in the upper triangle of the matrix
                        // and when they are not the first two most dissimilar users
                        if ((col > row)
                            && (row != dissimilar_user1 || col != dissimilar_user2)) {
                            // print a comma
                            System.out.print(",");
                            // print a newline
                            System.out.print("\n");
                            // print the other most dissimilar pair of users
                            System.out
                            .print("User" + (row + 1) + " and " + "User" + (col + 1));
                        }
                    }
                }
            }
        }
        // print the lowest similarity score
        System.out.println(
                           "\n" + "with similarity score of " + df4.format(lowest_sim_score));
    }
}
