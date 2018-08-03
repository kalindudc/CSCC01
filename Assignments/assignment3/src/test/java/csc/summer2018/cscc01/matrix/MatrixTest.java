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
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                int distancePower = 0;
                for (int inner = 0; inner < 2; inner++) {
                    distancePower += Math.pow((matrix.getPoint(x,inner).getValue().floatValue() - matrix.getPoint(y,inner).getValue().floatValue()), 2);
                }
                expected.addPoint(new Point(x, y, (float) (1/(1+Math.sqrt(distancePower)))));
            }

        }

        assertTrue(expected.equals(matrix.generateEuclideanDistanceMatrix()));
    }
}