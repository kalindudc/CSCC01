package csc.summer2018.cscc01.matrix;

import java.util.ArrayList;
import java.util.List;

public class EucledianSymmetricMatrix extends Matrix implements EuclideanSymmetricMatrixInterface {

    /**
     * Create an instance of a this symmetric matrix
     * @param numRows the number of rows in this matrix, also equivalent to the number of columns
     */
    public EucledianSymmetricMatrix (int numRows) {
        super(numRows, numRows);
    }

    public List<Point<Number>> getUpperTriangularMaxPoints () {
        List<Point<Number>> res = new ArrayList<Point<Number>>();
        Number current = Float.MIN_VALUE;
        for (int x = 0; x < super.getNumRows(); x++) {
            for (int y = x; y < super.getNumColumns(); y++) {
                if (current.floatValue() < super.getPoint(x, y).getValue().floatValue() && x != y) {
                    res.clear();
                    res.add(super.getPoint(x, y));
                    current = super.getPoint(x, y).getValue();
                }
                else if (current.floatValue() == super.getPoint(x, y).getValue().floatValue() && x != y) {
                    res.add(super.getPoint(x, y));
                }
            }
        }
        return res;
    }

    /**
     * Add a given point symmetrically to (x,y) and (y,x)
     * @param point the point to add
     */
    @Override
    public void addPoint(Point<Number> point) {
        super.addPoint(point);
        super.addPoint(new Point<Number>(point.getY(), point.getX(), point.getValue()));
    }

    public List<Point<Number>> getUpperTriangularMinPoints () {
        List<Point<Number>> res = new ArrayList<Point<Number>>();
        Number current = Float.MAX_VALUE;
        for (int x = 0; x < super.getNumRows(); x++) {
            for (int y = x; y < super.getNumColumns(); y++) {
                if (current.floatValue() > super.getPoint(x, y).getValue().floatValue() && x != y) {
                    res.clear();
                    res.add(super.getPoint(x, y));
                    current = super.getPoint(x, y).getValue();
                }
                else if (current.floatValue() == super.getPoint(x, y).getValue().floatValue() && x != y) {
                    res.add(super.getPoint(x, y));
                }
            }
        }
        return res;
    }
}
