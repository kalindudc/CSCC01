package csc.summer2018.cscc01;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

            // TODO:1.) CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
            // call the calculateSimilarityScore method
            // to calculate the similarity score between users
            cfObject.calculateSimilarityScore();
            // TODO:2.) PRINT OUT THE userUserMatrix
            // print two line breaks
            System.out.println("\n");
            // call the printUserUserMatrix method to print the userUserMatrix
            System.out.println("userUserMatrix is: ");
            cfObject.printUserUserMatrix();
            // TODO:3.) PRINT OUT THE MOST SIMILAR PAIRS OF USER AND THE MOST
            // DISSIMILAR
            // PAIR OF USERS.
            // print two line breaks
            System.out.println("\n");
            // call the findAndprintMostSimilarPairOfUsers method
            // to print out the most similar pair of users
            cfObject.findAndprintMostSimilarPairOfUsers();
            // print two line breaks
            System.out.println("\n");
            // call the findAndprintMostDissimilarPairOfUsers method
            // to print out the most dissimilar pair of users
            cfObject.findAndprintMostDissimilarPairOfUsers();
        } catch (FileNotFoundException e) {
            System.err.println("Do you have the input file in the root folder "
                               + "of your project?");
            System.err.print(e.getMessage());
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
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
