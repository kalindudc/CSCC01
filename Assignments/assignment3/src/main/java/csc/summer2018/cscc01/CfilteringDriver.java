package csc.summer2018.cscc01;
import csc.summer2018.cscc01.matrix.Matrix;
import csc.summer2018.cscc01.matrix.Point;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class CfilteringDriver {
    
    /**
     * Reads user movie ratings from a text file, calculates similarity scores and
     * prints a score matrix.
     */
    public static void main(String[] args) {
        try {
            // open file to read
            String fileName;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the name of input file? ");
            fileName = in.nextLine();
            Cfiltering cfObject = getCfilteringObject(fileName);
            // calculate the similarity score between users
            cfObject.calculateSimilarityScore();
            System.out.println("\n");
            // print the userUserMatrix
            System.out.println("userUserMatrix is: ");
            System.out.println(cfObject.getUserUserMatrix());
            System.out.println("\n");
            //  print out the most similar pair of users
            printMostSimilar(cfObject.getMostSimilarPairOfUsers());
            // print two line breaks
            System.out.println("\n");
            // print out the most dissimilar pair of users
            printMostDissimilar(cfObject.getMostDisimilarPairOfUsers());
        } catch (FileNotFoundException e) {
            System.err.println("Do you have the input file in the root folder "
                               + "of your project?");
            System.err.print(e.getMessage());
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }

    /**
     * Give an list of similar points print them out
     * @param similar the list of similar points
     */
    private static void printMostSimilar(List<Point<Number>> similar) {
        // print the first two users with the highest similarity score
        System.out.println(
                "The most similar pairs of users from above userUserMatrix are: ");
        String users = "";
        Float score = 0.0f;
        for (int i = 0; i < similar.size(); i++) {
            users += "User" + (similar.get(i).getX() + 1) + " and User" + (similar.get(i).getY() + 1) + "\n";
            score = similar.get(i).getValue().floatValue();
        }
        System.out.println(users.substring(0, (users.length() - 1)));
        System.out.println("with similarity score of " + Matrix.DECIMAL_FORMAT.format(score));
    }

    /**
     * Given a list of dissimilar points print them out
     * @param dissimilar the list of dissimilar points
     */
    private static void printMostDissimilar(List<Point<Number>> dissimilar) {
        // print the first two users with the highest similarity score
        System.out.println(
                "The most similar pairs of users from above userUserMatrix are: ");
        String users = "";
        Float score = 0.0f;
        for (int i = 0; i < dissimilar.size(); i++) {
            if (dissimilar.get(i).getX() != dissimilar.get(i).getY()) {
                users += "User" + (dissimilar.get(i).getX() + 1) + " and User" + (dissimilar.get(i).getY() + 1) + "\n";
                score = dissimilar.get(i).getValue().floatValue();
            }
        }
        System.out.println(users.substring(0, (users.length() - 1)));
        System.out.println("with similarity score of " + Matrix.DECIMAL_FORMAT.format(score));
    }

    /**
     * Read a given file and generate a Cfiltering object
     *
     * @param file the name of the file to read
     * @return the generated cfiltering object
     * @throws IOException
     */
    private static Cfiltering getCfilteringObject(String file) throws IOException {
        FileInputStream fStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
        // Read dimensions: number of users and number of movies
        int numberOfUsers = Integer.parseInt(br.readLine());
        int numberOfMovies = Integer.parseInt(br.readLine());
        Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);
        // this is a blankline being read
        br.readLine();
        // read each line of movie ratings and populate the userMovieMatrix
        String row;
        // initiate a row_counter at 0 to specify the row when populating userMovieMatrix
        int row_counter = 0;
        while ((row = br.readLine()) != null) {
            // allRatings is a list of all String numbers on one row
            String allRatings[] = row.split(" ");
            // initiate a col_counter at 0 to specify the column when populating userMovieMatrix
            int col_counter = 0;
            for (String singleRating : allRatings) {
                // make the String number into an integer
                int intsingleRating = Integer.parseInt(singleRating);
                // to populate the userMovieMatrix
                cfObject.populateUserMovieMatrix(row_counter, col_counter,
                        intsingleRating);
                col_counter++;
            }
            row_counter++;
        }
        // close the file
        fStream.close();

        return cfObject;
    }
}
