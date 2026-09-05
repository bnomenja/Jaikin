import java.util.ArrayList;
import java.util.List;

public class Algo {
    public static List<Point> generatePoints(List<Point> allPoints) {
        List<Point> newPoints = new ArrayList<>();

        if (allPoints.isEmpty()) {
            return newPoints;
        }

        Point firstPoint = allPoints.get(0);
        newPoints.add(new Point(firstPoint.getX(), firstPoint.getY()));

        for (int i = 0; i < allPoints.size() - 1; i++) {
            Point p1 = allPoints.get(i);
            Point p2 = allPoints.get(i + 1);

            double qX = (3 * p1.getX() + p2.getX()) / 4;
            double qY = (3 * p1.getY() + p2.getY()) / 4;
            double rX = (p1.getX() + 3 * p2.getX()) / 4;
            double rY = (p1.getY() + 3 * p2.getY()) / 4;

            newPoints.add(new Point(qX, qY));
            newPoints.add(new Point(rX, rY));
        }

        Point lastPoint = allPoints.get(allPoints.size() - 1);
        newPoints.add(new Point(lastPoint.getX(), lastPoint.getY()));

        return newPoints;
    }
}
