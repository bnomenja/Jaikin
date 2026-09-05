import java.util.ArrayList;
import java.util.List;

public class Algo {
    public static List<Point> generatePoints(List<Point> allPoints) {
        List<Point> list = new ArrayList<>();

        if (allPoints.size() == 0) {
            return list;
        }

        if (allPoints.size() == 1) {
            list.add(allPoints.get(0));
            return list;
        }

        list.add(allPoints.get(0));
        
        for (int i = 0; i < allPoints.size() - 1; i++) {

            Point p0 = allPoints.get(i);
            Point p1 = allPoints.get(i + 1);

            int qx = (int) (0.75 * p0.getX() + 0.25 * p1.getX());
            int qy = (int) (0.75 * p0.getY() + 0.25 * p1.getY());

            int rx = (int) (0.25 * p0.getX() + 0.75 * p1.getX());
            int ry = (int) (0.25 * p0.getY() + 0.75 * p1.getY());

            list.add(new Point(qx, qy));
            list.add(new Point(rx, ry));
        }


        list.add(allPoints.get(allPoints.size() - 1));

        return list;
    }
}
