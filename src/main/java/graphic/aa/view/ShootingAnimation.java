package graphic.aa.view;

import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;

public class ShootingAnimation extends Transition {

    private ArrayList<Circle> circles;
    private ArrayList<Line> lines;
    private ArrayList<Timeline> timelines;

    private Circle circle;

    private int degree;
    private Pane pane;

    public ShootingAnimation(ArrayList<Circle> circles, ArrayList<Line> lines, ArrayList<Timeline> timelines,
                             Circle circle, Pane pane, int degree) {
        this.lines = lines;
        this.circles = circles;
        this.timelines = timelines;
        this.circle = circle;
        this.pane = pane;
        this.degree = degree;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        double y = circle.getCenterY() - 10;
        double radian = Math.toRadians(degree);
        double x = circle.getCenterX() - (10 * Math.tan(radian));
        if (bomb()) {
            GameMenu.setFinish(true);
            for (int i = 0; i < timelines.size(); i++) {
                timelines.get(i).stop();
            }
            this.stop();
        }

        if (isNearDistance(x,y)) {
            circles.add(circle);
            Line line = new Line(400, 300, x, y);
            pane.getChildren().add(line);
            lines.add(line);
            this.stop();
        }
        circle.setCenterY(y);
        circle.setCenterX(x);
    }

    private boolean bomb() {
        for (Circle circle1 : circles) {
            if (circle.getBoundsInParent().intersects(circle1.getBoundsInParent()) && !circle1.equals(circle))
                return true;
        }
        return false;
    }

    private boolean isNearDistance(double x,double y) {
        return Math.sqrt((x - 400) * (x - 400) + (y - 300) * (y - 300)) <= 220;
    }
}
