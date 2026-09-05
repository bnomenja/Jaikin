import java.util.ArrayList;
import java.util.List;

public class Manager {
    private final List<Point> allPoints;
    private List<Point> ChaikinPoints;
    private boolean isAnimating;
    private int currentStep;
    private final int maxSteps = 7;
    private String statusMessage;

    public Manager() {
        this.allPoints = new ArrayList<>();
        this.ChaikinPoints = new ArrayList<>();
        this.isAnimating = false;
        this.currentStep = 0;
        this.statusMessage = "Left-click to add control points. Press Enter to animate.";
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

    public boolean shouldDrawCurve() {
        return this.isAnimating || this.allPoints.size() == 2;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void addPoint(int x, int y) {
        if (this.isAnimating) {
            return;
        }
        Point p = new Point(x, y);
        this.allPoints.add(p);
        this.ChaikinPoints.clear();
        this.currentStep = 0;
        this.statusMessage = "Control points: " + this.allPoints.size();
    }

    public void removeAllPoints() {
        this.allPoints.clear();
        this.ChaikinPoints.clear();
        this.currentStep = 0;
        this.isAnimating = false;
        this.statusMessage = "Canvas cleared. Left-click to add control points.";
    }

    public void startAnimation() {
        if (allPoints.isEmpty()) {
            this.statusMessage = "Add at least one control point before pressing Enter.";
            return;
        }
        if (allPoints.size() == 1) {
            this.statusMessage = "One control point: no curve can be generated.";
            return;
        }
        if (allPoints.size() == 2) {
            this.ChaikinPoints = copyPoints(this.allPoints);
            this.statusMessage = "Two control points: displaying a straight line.";
            return;
        }
        this.currentStep = 0;
        this.ChaikinPoints = copyPoints(this.allPoints);
        this.isAnimating = true;
        this.statusMessage = "Chaikin step 0 of " + maxSteps;
    }

    public void nextStep() {
        if (!isAnimating) {
            return;
        }
        if (currentStep >= maxSteps) {
            this.ChaikinPoints = copyPoints(this.allPoints);
            this.currentStep = 0;
            this.statusMessage = "Chaikin step 0 of " + maxSteps;
            return;
        }

        this.ChaikinPoints = Algo.generatePoints(this.ChaikinPoints);
        this.currentStep++;
        this.statusMessage = "Chaikin step " + currentStep + " of " + maxSteps;
    }

    private List<Point> copyPoints(List<Point> points) {
        List<Point> copy = new ArrayList<>();
        for (Point point : points) {
            copy.add(new Point(point.getX(), point.getY()));
        }
        return copy;
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