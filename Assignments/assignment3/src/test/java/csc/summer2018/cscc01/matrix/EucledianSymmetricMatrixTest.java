package csc.summer2018.cscc01.matrix;

import junit.framework.TestCase;

import java.util.List;

public class EucledianSymmetricMatrixTest extends TestCase {

    private EucledianSymmetricMatrix matrix;

    public void setUp () throws Exception {

        super.setUp();

        matrix = new EucledianSymmetricMatrix(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix.addPoint(new Point<Number>(i, j, i+j));
            }
        }
    }

    public void tearDown () throws Exception {

    }

    public void testGetUpperTriangularMaxPoints () {
        List<Point<Number>> max = matrix.getUpperTriangularMaxPoints();
        assertEquals(1, max.size());
        assertEquals(new Point<Number>(1,2,3), max.get(0));

    }

    public void testGetUpperTriangularMinPoints () {
        List<Point<Number>> max = matrix.getUpperTriangularMinPoints();
        assertEquals(1, max.size());
        assertEquals(new Point<Number>(0,1,1), max.get(0));
    }
};