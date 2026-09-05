import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Point> allPoints;
    private List<Point> ChaikinPoints;
    private boolean isAnimating;

    public Manager() {
        this.allPoints = new ArrayList<>();
        this.ChaikinPoints = new ArrayList<>();
        this.isAnimating = false;
    }

    public List<Point> getAllPoints() {
        return this.allPoints;
    }

    public List<Point> getChakinPoints() {
        return this.ChaikinPoints;
    }

    public boolean isAnimating() {
        return this.isAnimating;
    }

    public void setAnimationState(boolean state) {
        this.isAnimating = state;
    }

    public void addPoint(int x, int y) {
        if (this.isAnimating) {
            return;
        }
        Point p = new Point(x, y);
        this.allPoints.add(p);
    }

    public void removeAllPoints() {
        this.allPoints.clear();
        this.ChaikinPoints.clear();
        this.isAnimating = false;
    }

    public void startAnimation() {
        if (this.isAnimating) {
            return;
        }
        if (allPoints.size() < 2) {
            return;
        }

        this.ChaikinPoints = Algo.generatePoints(this.allPoints);
        this.isAnimating = true;
    }

    public void nextStep() {
        if (!isAnimating) {
            return;
        }

        List<Point> newPoints = Algo.generatePoints(this.ChaikinPoints);

        this.ChaikinPoints = newPoints;
    }

    public void printAllPoints() {
        for (int i = 0; i < allPoints.size(); i++) {
            Point p = allPoints.get(i);

            if (i == 0) {
                System.out.printf(
                    "Point%d : %d, %d",
                    i + 1,
                    p.getX(),
                    p.getY()
                );
            } else {
                System.out.printf(
                    "; Point%d : %d, %d",
                    i + 1,
                    p.getX(),
                    p.getY()
                );
            }
        }

        System.out.println();
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}