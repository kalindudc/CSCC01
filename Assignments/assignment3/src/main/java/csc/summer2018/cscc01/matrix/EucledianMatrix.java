package csc.summer2018.cscc01.matrix;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EucledianMatrix extends Matrix implements EuclideanMatrixInterface {

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0000");

    public EucledianMatrix (int numRows) {
        super(numRows, numRows);
    }

    public List<Point<Number>> getMaxPoints() {
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

    public List<Point<Number>> getMinPoints() {
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
