import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Point> allPoints;
    private List<Point> chaikinPoints;
    private boolean isAnimating;
    private int currentStep;
    private final int maxSteps = 7;
    private String statusMessage;
    private final long stepIntervalMs = 500;
    private long lastStepTime;

    public Manager() {
        this.allPoints = new ArrayList<>();
        this.chaikinPoints = new ArrayList<>();
        this.isAnimating = false;
        this.currentStep = 1;
        this.statusMessage = "Left-click to add control points. Press Enter to animate.";
    }

    public List<Point> getAllPoints() {
        return copyPoints(this.allPoints);
    }

    public List<Point> getChakinPoints() {
        return copyPoints(this.chaikinPoints);
    }

    public boolean isAnimating() {
        return this.isAnimating;
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
        this.statusMessage = "Control points: " + this.allPoints.size();
    }

    public void removeAllPoints() {
        this.allPoints.clear();
        this.chaikinPoints.clear();
        this.currentStep = 1;
        this.isAnimating = false;
        this.statusMessage = "Canvas cleared. Left-click to add control points.";
    }

    public void startAnimation() {
        if (allPoints.isEmpty()) {
            this.statusMessage = "Add at least one control point before pressing Enter.";
            return;
        }
        this.isAnimating = true;
        this.lastStepTime = System.currentTimeMillis();
        if (allPoints.size() == 1) {
            this.statusMessage = "One control point: no curve can be generated.";
            return;
        }
        if (allPoints.size() == 2) {
            this.chaikinPoints = copyPoints(this.allPoints);
            this.statusMessage = "Two control points: displaying a straight line.";
            return;
        }
        this.currentStep = 1;
        this.chaikinPoints = copyPoints(this.allPoints);
        this.statusMessage = "Chaikin step 1 of " + maxSteps;
    }

    public void nextStep() {
        if (!isAnimating || allPoints.size() < 3) {
            return;
        }

        if (currentStep >= maxSteps) {
            this.chaikinPoints = copyPoints(this.allPoints);
            this.currentStep = 1;
            this.statusMessage = "Chaikin step 1 of " + maxSteps;
            return;
        }

        this.chaikinPoints = Algo.generatePoints(this.chaikinPoints);
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


    public void update() {
        if (!isAnimating) {
            return;
        }
        
        long now = System.currentTimeMillis();
        if (now - lastStepTime >= stepIntervalMs) {
            nextStep();
            lastStepTime = now;
        }
    }
}