package csc.summer2018.cscc01.matrix;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MatrixTest extends TestCase {

    private Matrix matrix;
    public void setUp () throws Exception {
        super.setUp();
        matrix = new Matrix(2, 2);
    }

    public void tearDown () throws Exception {
    }

    public void testAddPoint () {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix.addPoint(new Point<Number>(i, j, i+j));
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(matrix.getPoint(i, j), new Point<Number>(i, j, i+j));
            }
        }
    }

    public void testGenerateEuclideanDistanceMatrix () {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix.addPoint(new Point<Number>(i, j, i+j));
            }
        }

        Matrix expected = new Matrix(2, 2);
        System.out.println(expected);
        assertEquals(expected, matrix.generateEuclideanDistanceMatrix());
    }
}